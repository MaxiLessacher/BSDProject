﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
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
    /// <summary>
    /// Interaktionslogik für MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        Database db = null;
        public MainWindow()
        {
            InitializeComponent();
        }

        private void bttnSearch_Click(object sender, RoutedEventArgs e)
        {
            db = new Database("Provider=OraOLEDB.Oracle; Data Source=192.168.128.151/ora11g;User Id = d5b30;Password =d5b30; OLEDB.NET=True;");
            //db = new Database("Provider=OraOLEDB.Oracle; Data Source=212.152.179.117/ora11g;User Id = d5b30;Password =d5b30; OLEDB.NET=True;");
            db.connect();
            Console.WriteLine("Connected");
            db.setStadt(txtBoxStadt.Text);
            db.printHaushalte();

            Canvas can = new Canvas();

            for (int i = 0; i < db.Haushalte.Count; i++)
            {
                for (int j = 0; j < db.Haushalte.ElementAt(i).wasserzaehler.Count; j++)
                {
                    Rectangle rec = new Rectangle();
                    rec.Width = 5;
                    rec.Height = 5;
                    rec.Fill = new SolidColorBrush(Colors.Blue);
                    rec.MouseLeftButtonDown +=rec_MouseLeftButtonDown;
                    //rec.AddHandler(Rectangle.MouseLeftButtonDownEvent, new RoutedEventHandler(rec_click));
                    rec.ToolTip = db.Haushalte.ElementAt(i).name + " " + db.Haushalte.ElementAt(i).number + " , " + db.Haushalte.ElementAt(i).plz + " " + txtBoxStadt.Text;
                    Canvas.SetLeft(rec,db.Haushalte.ElementAt(i).wasserzaehler.ElementAt(j).standort.X);
                    Canvas.SetTop(rec, db.Haushalte.ElementAt(i).wasserzaehler.ElementAt(j).standort.Y);
                    can.Children.Add(rec);
                }
            }
            this.scrollPanel.Content = can;
        }

        private void rec_MouseLeftButtonDown(object sender, RoutedEventArgs e)
        {
            Rectangle rec = (Rectangle) sender;
            txtBoxAdresse.Text = rec.ToolTip.ToString();
        }
    }
}