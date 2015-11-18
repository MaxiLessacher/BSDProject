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
        public List<Haushalt> Haushalte = new List<Haushalt>();
        private String Stadt { get; set; }
        private PointCollection pc = new PointCollection();

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
            var reader = new OleDbCommand("Select * from haushalt h where plz = (select plz from ort where name = '" + Stadt + "')", this.connection).ExecuteReader();

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
                Haushalte.Add(new Haushalt(id, name, plz, number, tuernr, wohnflaeche, landWirtschaftBool, gartenBool));
            }
            connection.Close();
        }

        public void fillWasserzaehler(){
            for (int i = 0; i < Haushalte.Count; i++)
            {
                connect();
                //var reader = new OleDbCommand("Select zaehler_nr, w.hh_id, zaehlerstand, hauptzaehler from haushalt h inner join wasserzaehler w on h.hh_id = w.HH_ID where h.HH_ID = '" + (i+1) + "' and h.plz = '" + Haushalte.ElementAt(i).plz + "'", this.connection).ExecuteReader();
                var reader = new OleDbCommand("Select zaehler_nr, w.hh_id, zaehlerstand, hauptzaehler from haushalt h inner join wasserzaehler w on h.hh_id = w.HH_ID where h.hh_id = '" + Haushalte.ElementAt(i).hid + "'", this.connection).ExecuteReader();
                while(reader.Read())
                {
                    Console.WriteLine(Haushalte.Count);
                    int zaehler_nr = Convert.ToInt32(reader["zaehler_nr"].ToString());
                    int hhid = Convert.ToInt32(reader["hh_id"].ToString());
                    int zaehlerstand = Convert.ToInt32(reader["zaehlerstand"].ToString());
                    Point p = new Point();
                    int isHauptzaehler = Convert.ToInt32(reader["Hauptzaehler"].ToString());
                    Boolean hauptZaehlerBool = false;
                    if(isHauptzaehler == 1)
                    {
                        hauptZaehlerBool = true;
                    }
                    //var reader2 = new OleDbCommand("select w.zaehler_nr, t.x, t.y FROM wasserzaehler w inner join haushalt h on h.HH_ID = w.HH_ID AND h.hh_id = '" + (i+1) + "' , TABLE(SDO_UTIL.GETVERTICES(w.standort)) t", this.connection).ExecuteReader();
                    //var reader2 = new OleDbCommand("Select w.zaehler_nr, t.x, t.y FROM wasserzaehler w where w.zaehler_nr = '" + zaehler_nr + "' , TABLE(SDO_UTIL.GETVERTICES(w.standort)) t", this.connection).ExecuteReader();
                    var reader2 = new OleDbCommand("select w.zaehler_nr, t.x, t.y FROM wasserzaehler w inner join haushalt h on h.HH_ID = w.HH_ID AND h.hh_id = '" + Haushalte.ElementAt(i).hid + "' , TABLE(SDO_UTIL.GETVERTICES(w.standort)) t", this.connection).ExecuteReader();
                    while(reader2.Read())
                    {
                        p.X = Convert.ToInt32(reader2["X"]);
                        p.Y = Convert.ToInt32(reader2["Y"]);
                    }
                    Haushalte.ElementAt(i).addWasserzaehler(zaehler_nr, hhid, zaehlerstand, p, hauptZaehlerBool);
                    Console.WriteLine("X: " + p.X + ", Y:" + p.Y);
                    System.Drawing.Point p2 = new System.Drawing.Point(p.X, p.Y);
                }
            }
        }

        public void printHaushalte()
        {
            fillHaushalte();
            fillWasserzaehler();
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
