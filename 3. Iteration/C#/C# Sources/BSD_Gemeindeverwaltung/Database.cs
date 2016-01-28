using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.OleDb;
using System.Drawing;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace BSD_Gemeindeverwaltung
{
    class Database
    {
        private  OleDbConnection connection = null;
        private  OleDbTransaction transe = null;
        private  String connStr = "";
        private String Stadt { get; set; }
        private PointCollection pc = new PointCollection();
        public List<Haushalt> Haushalte = new List<Haushalt>();
        public List<Mitglied> Mitglieder = new List<Mitglied>();

        public Database() { }

        public Database(String _connStr)
        {
            connStr = _connStr;
            this.connect();

        }

        public void setStadt(String pStadt)
        {
            Stadt = pStadt;
        }

        public OleDbConnection Connection
        {
            get
            {
                return connection;
            }

            set
            {
                connection = value;
            }
        }

        private void fillHaushalte()
        {
            //var reader = new OleDbCommand("Select * from haushalt h where plz = (select plz from ort where name = '" + Stadt + "')", this.connection).ExecuteReader();
            var reader = new OleDbCommand("Select h.hh_id, h.name, h.plz, h.nummer, h.tuernr, h.wohnflaeche, h.landwirtschaft, h.garten, o.name from haushalt h inner join adresse ad on h.plz = ad.plz join strasse st on st.plz = ad.plz join ort o on o.plz = st.plz", this.connection).ExecuteReader();
            while (reader.Read())
            {
                int id = Convert.ToInt32(reader["hh_id"].ToString());
                String name = Convert.ToString(reader["Name"].ToString());
                int plz = Convert.ToInt32(reader["plz"].ToString());
                int tuernr = Convert.ToInt32(reader["tuernr"].ToString());
                int number = Convert.ToInt32(reader["nummer"].ToString());
                int wohnflaeche = Convert.ToInt32(reader["wohnflaeche"].ToString());
                int landwirtschaft = Convert.ToInt32(reader["landwirtschaft"].ToString());
                int garten = Convert.ToInt32(reader["garten"].ToString());
                String ort = reader[8].ToString();
                Console.WriteLine(name + " " + ort);
                Boolean landWirtschaftBool = false;
                Boolean gartenBool = false;
                if (landwirtschaft == 1)
                {
                    landWirtschaftBool = true;
                }
                if (garten == 1)
                {
                    gartenBool = true;
                }
                Haushalte.Add(new Haushalt(id, name, plz, number, tuernr, wohnflaeche, landWirtschaftBool, gartenBool, ort));
            }
            connection.Close();
        }

        public void fillWasserzaehler(){
            for (int i = 0; i < Haushalte.Count; i++)
            {
                connect();
                var reader = new OleDbCommand("Select w.zaehler_nr, w.hauptzaehler, w.zaehlerstand, h.hh_id, t.x, t.y from wasserzaehler w inner join haushalt h on h.hh_id = w.hh_id and h.hh_id = '" + Haushalte.ElementAt(i).hid +"' , TABLE(SDO_UTIL.GETVERTICES(w.standort)) t", this.connection).ExecuteReader();
                while(reader.Read())
                {
                    Console.WriteLine(Haushalte.Count);
                    int zaehler_nr = Convert.ToInt32(reader["zaehler_nr"].ToString());
                    int hhid = Convert.ToInt32(reader["hh_id"].ToString());
                    int zaehlerstand = Convert.ToInt32(reader["zaehlerstand"].ToString());
                    Point p = new Point();
                    p.X = Convert.ToInt32(reader["X"]);
                    p.Y = Convert.ToInt32(reader["Y"]);
                    int isHauptzaehler = Convert.ToInt32(reader["Hauptzaehler"].ToString());
                    Boolean hauptZaehlerBool = false;
                    if(isHauptzaehler == 1)
                    {
                        hauptZaehlerBool = true;
                    }
                    Haushalte.ElementAt(i).addWasserzaehler(zaehler_nr, hhid, zaehlerstand, p, hauptZaehlerBool);
                    Console.WriteLine("X: " + p.X + ", Y:" + p.Y);
                }
            }
        }

        public void fillMitglieder()
        {
            connect();
            var reader = new OleDbCommand("SELECT mitglieds_id, name, hh_vorstand, hh_id FROM mitglieder", this.connection).ExecuteReader();
            while (reader.Read())
            {
                int mit = Convert.ToInt32(reader["mitglieds_id"]);
                String name = reader["name"].ToString();
                int hhv = Convert.ToInt32(reader["hh_vorstand"]);
                int hhi = Convert.ToInt32(reader["hh_id"]);
                Boolean hh_v = false;
                if (hhv == 1) hh_v = true;
                Mitglieder.Add(new Mitglied(mit, name, hh_v, hhi));
            }

        }

        public void printHaushalte()
        {
            fillHaushalte();
            fillWasserzaehler();
            fillMitglieder();
            foreach (Haushalt h in Haushalte)
            {
                Console.WriteLine(h.ToString());
            }
        }

        public PointCollection getPoints()
        {
            pc.Add(new System.Windows.Point(5, 25));
            return this.pc;
        }

        //try to open a connection to oracle
        public void connect()
        {
            connection = new OleDbConnection(connStr);
            connection.Open();

        }

        public void startTranse()
        {
            if (this.transe != null)
            {
                transe.Rollback();
            }
            this.transe = connection.BeginTransaction();
        }

        private void execNQ(string sql)
        {
            new OleDbCommand(sql, this.Connection, this.transe)
                .ExecuteNonQuery();
        }

        public void Commit()
        {
            new OleDbCommand("COMMIT", this.Connection, this.transe)
                .ExecuteNonQuery();
        }

        public void Rollback()
        {
            this.execNQ("ROLLBACK");
        }


        //close the connection
        public void close()
        {
            connection.Close();
        }
    }
}
