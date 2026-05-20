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
    /// Lógica de interacción para ListaEmpleados.xaml
    /// </summary>
    public partial class ListaEmpleados : Window
    {

        private SqlConnection conexionConSql;

        public ListaEmpleados()
        {
            InitializeComponent();
            EstablecerConexion();
            CargarEmpleados();
        }

        private void EstablecerConexion()
        {
            string CadenaDeConexion = ConfigurationManager.ConnectionStrings
                ["GestionEmpleados2026.Properties.Settings.GestionEmpleadosConnectionString"].ConnectionString;
            conexionConSql = new SqlConnection(CadenaDeConexion);
        }

        private void CargarEmpleados()
        {
            // Lista para asignar en el DataGrid
            List<Empleado> empleados = new List<Empleado>();

            // Abro conexion y ejecuto la consulta
            conexionConSql.Open();
            SqlCommand cmd = new SqlCommand("SELECT Nombre, Apellidos, EsUsuario, Edad FROM Empleados", conexionConSql);
            SqlDataReader reader = cmd.ExecuteReader();

            // Por cada fila leida creo un empleado y lo añado a la lista
            while (reader.Read())
            {
                Empleado e = new Empleado();
                e.Nombre = reader["Nombre"].ToString();
                e.Apellidos = reader["Apellidos"].ToString();
                e.EsUsuario = (bool)reader["EsUsuario"];
                e.Edad = (int)reader["Edad"];
                empleados.Add(e);
            }
            conexionConSql.Close();

            // Asigno la lista al DataGrid 
            dataGrid.ItemsSource = empleados;
        }

        private void btnEliminarRegistro_Click(object sender, EventArgs e)
        {
            // Compruebo que hay una fila seleccionada en DataGrid
            if (dataGrid.SelectedItem == null)
            {
                MessageBox.Show("Selecciona un empleado para eliminar", "Aviso", MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }

            // Casteo la fila seleccionada a Empleado para acceder a sus datos
            Empleado empleadoSeleccionado = (Empleado)dataGrid.SelectedItem;

            // Pido confirmacion antes de borrar
            MessageBoxResult confirmacion = MessageBox.Show(
                $"¿seguro que quieres eliminar a {empleadoSeleccionado.Nombre} {empleadoSeleccionado.Apellidos}?",
                "Confirmar eliminacion",
                MessageBoxButton.YesNo,
                MessageBoxImage.Question);

            if (confirmacion == MessageBoxResult.No)
                return;

            string query = "DELETE FROM Empleados WHERE Nombre = @Nombre AND Apellidos = @Apellidos";

            conexionConSql.Open();
            SqlCommand cmd = new SqlCommand(query, conexionConSql);
            cmd.Parameters.AddWithValue("@Nombre", empleadoSeleccionado.Nombre);
            cmd.Parameters.AddWithValue("@Apellidos", empleadoSeleccionado.Apellidos);
            cmd.ExecuteNonQuery();
            conexionConSql.Close();

            // Recargo el DataGrid para ver los cambios
            CargarEmpleados();

            MessageBox.Show("Empleado eliminado correctamente");
        }

    }
}
