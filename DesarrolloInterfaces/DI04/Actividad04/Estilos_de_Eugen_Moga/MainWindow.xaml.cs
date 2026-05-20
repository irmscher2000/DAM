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

namespace Estilos_de_Eugen_Moga
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

        }

        private void Inicio_Click(object sender, RoutedEventArgs e)
        {
            MainFrame.Navigate(new MainPage());
        }

        private void Sobre_Click(object sender, RoutedEventArgs e)
        {
            MainFrame.Navigate(new Sobre());
        }

        private void Servicio_Click(object sender, RoutedEventArgs e) 
        {
            MainFrame.Navigate(new ServiciosPage());
        }

        private void Contacto_Click(object sender, RoutedEventArgs e)
        {
            MainFrame.Navigate(new ContactoPage());
        }

        
       
    }
}
