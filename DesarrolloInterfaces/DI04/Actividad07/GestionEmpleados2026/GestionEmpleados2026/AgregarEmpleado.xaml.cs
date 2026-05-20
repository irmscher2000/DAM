using System;
using System.Collections.Generic;
using System.Configuration;
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
using System.Windows.Shapes;

namespace GestionEmpleados2026
{
    /// <summary>
    /// Lógica de interacción para AgregarEmpleado.xaml
    /// </summary>
    public partial class AgregarEmpleado : Window
    {

        private SqlConnection conexionConSql;

        public AgregarEmpleado()
        {
            InitializeComponent();
            EstablecerConexion();
        }

        private void EstablecerConexion()
        {
            string CadenaDeConexion = ConfigurationManager.ConnectionStrings
                ["GestionEmpleados2026.Properties.Settings.GestionEmpleadosConnectionString"].ConnectionString;
            conexionConSql = new SqlConnection(CadenaDeConexion);
        }

        private void btnAgregarEmpleado_Click(object sender, RoutedEventArgs e)
        {
            // Recogo los valores del formulario
            string nombre = Nombre.Text;
            string apellidos = Apellidos.Text;
            bool esUsuario = chkUsuario.IsChecked == true;
            int edad = int.Parse(Edad.Text);

            // Construyo el query con los parametros para insertar en la base de datos
            string query = "INSERT Empleados (Nombre, Apellidos, EsUsuario, Edad) VALUES (@Nombre, @Apellidos, @EsUsuario, @Edad)";

            conexionConSql.Open();
            SqlCommand cmd = new SqlCommand(query, conexionConSql);

            // Asigno cada parametro con su valor
            cmd.Parameters.AddWithValue("@Nombre", nombre);
            cmd.Parameters.AddWithValue("Apellidos", apellidos);
            cmd.Parameters.AddWithValue("@EsUsuario", esUsuario);
            cmd.Parameters.AddWithValue("@Edad", edad);

            // Ejecuto el query
            cmd.ExecuteNonQuery();
            conexionConSql.Close();

            MessageBox.Show("Empleado agregado correctamente.");
            this.Close();

        }

    }
}
