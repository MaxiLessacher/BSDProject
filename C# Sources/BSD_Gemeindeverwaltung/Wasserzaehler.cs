using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace BSD_Gemeindeverwaltung
{
    class Wasserzaehler
    {
        int zaehler_nr { get; set; }
        int hhid { get; set; }
        int zaehlerstand { get; set; }
        public Point standort { get; set; }
        public Boolean isHauptzaehler { get; set; }

        public Wasserzaehler(int pZaehler_nr, int pHhid, int pZaehlerstand, Point pStandort, Boolean pIsHauptzaehler)
        {
            this.zaehler_nr = pZaehler_nr;
            this.hhid = pHhid;
            this.zaehlerstand = pZaehlerstand;
            this.standort = pStandort;
            this.isHauptzaehler = pIsHauptzaehler;
        }

        public override string ToString()
        {
            return "X: " + this.standort.X + ", Y: " + this.standort.Y;
        }
    }
}
