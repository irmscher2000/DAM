using System;
using System.Configuration;
using System.Collections.Generic;
using System.Data.SqlClient;
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

namespace GestionEmpleados2026
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

        private void btnListaEmpleados_Click(object sender, RoutedEventArgs e)
        {
            ListaEmpleados ventana = new ListaEmpleados();
            ventana.Show();
        }

        private void btnAgregarEmpleado_Click(object sender, RoutedEventArgs e)
        {
            AgregarEmpleado ventana = new AgregarEmpleado();
            ventana.Show();
        }

        private void btnBuscarEmpleado_Click(Object sender, RoutedEventArgs e)
        {
            BuscarEmpleado AbrirBuscarEmpleado = new BuscarEmpleado();
            AbrirBuscarEmpleado.Show();
        }




    }
}
