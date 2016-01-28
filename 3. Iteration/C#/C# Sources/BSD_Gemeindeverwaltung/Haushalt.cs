using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace BSD_Gemeindeverwaltung
{
    class Haushalt
    {
        public int hid { get; set; }
        public String name { get; set; }
        public int plz { get; set; }
        public int number { get; set; }
        int tuernr { get; set; }
        int wohnflaeche { get; set; }
        Boolean landwirtschaft { get; set; }
        Boolean garten { get; set; }
        public String ort { get; set; }
        public List<Wasserzaehler> wasserzaehler = new List<Wasserzaehler>();

        public Haushalt(int pHid, String pName, int pPlz, int pNumber, int pTuernr, int pWohnflaeche, Boolean pLandwirtschaft, Boolean pGarten, String port)
        {
            this.hid = pHid;
            this.name = pName;
            this.plz = pPlz;
            this.number = pNumber;
            this.tuernr = pTuernr;
            this.wohnflaeche = pWohnflaeche;
            this.landwirtschaft = pLandwirtschaft;
            this.garten = pGarten;
            this.ort = port;
        }

        public void addWasserzaehler(int pZaehler_nr, int pHhid, int pZaehlerstand, Point pStandort, Boolean pIsHauptzaehler)
        {
            this.wasserzaehler.Add(new Wasserzaehler(pZaehler_nr, pHhid, pZaehlerstand, pStandort, pIsHauptzaehler));
        }

        public override string ToString()
        {
            return "Haushalt " + name + " , id" + hid + " , plz " + plz + " , nummer " + number + " , tuernr " + tuernr + " , wohnflaeche" + wohnflaeche + " , hasLandwirtschaft " + landwirtschaft + " , hasGarten " + garten;
        }

    }


}
