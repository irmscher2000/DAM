using Actividad_4._2.Models;
using System;
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

namespace Actividad_4._2
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            // Lista de personajes
            ComboBoxPersonajes.ItemsSource = new List<Personaje>
            {
                new Personaje{ Nombre="Máximo", Imagen="/Images/Maximo.png"},
                new Personaje{ Nombre="Lucio", Imagen="/Images/Lucio.png"},
                new Personaje{ Nombre="Proximo", Imagen="/Images/Proximo.png"}
            };
            

            // ListBox Modulos DAM
            ListBoxModulos.ItemsSource = new List<Modulo>
            {
                new Modulo{ Nombre="PMDM", Avance=85, Color=Brushes.Black},
                new Modulo{ Nombre="DW", Avance=80, Color=Brushes.Black},
                new Modulo{ Nombre="DI", Avance=50, Color=Brushes.Red},
                new Modulo{ Nombre="AD", Avance=40, Color=Brushes.Black},
                new Modulo{ Nombre="PSP", Avance=50, Color=Brushes.Black},
                new Modulo{ Nombre="SGE", Avance=80, Color=Brushes.Black}
            };
        }
    }
}
