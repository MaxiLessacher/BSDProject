using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BSD_Gemeindeverwaltung
{
    class Mitglied
    {
        public int mitglieds_id { get; set; }
        public String name { get; set; }
        Boolean hh_vorstand { get; set; }
        public int h_id { get; set; }

        public Mitglied(int m_id, String name, Boolean hh_v, int h_id)
        {
            this.mitglieds_id = m_id;
            this.name = name;
            this.hh_vorstand = hh_v;
            this.h_id = h_id;
        }
    }
}
