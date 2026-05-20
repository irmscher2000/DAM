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
    /// Lógica de interacción para ModificarEmpleadoWindow.xaml
    /// </summary>
    public partial class ModificarEmpleadoWindow : Window
    {

        private Empleado empleadoOriginal;  
        private string connectionString;

        public ModificarEmpleadoWindow(Empleado empleado, string cadenaConexion)
        {
            InitializeComponent();
            connectionString = cadenaConexion;
            empleadoOriginal = empleado;
            CargarDatosEmpleado();
        }

        private void EstablecerConexion()
        {
            string CadenaDeConexion = ConfigurationManager.ConnectionStrings
                ["GestionEmpleados2026.Properties.Settings.GestionEmpleadosConnectionString"].ConnectionString;
            connectionString = CadenaDeConexion;
        }

        private void CargarDatosEmpleado()
        {
            txtNombre.Text = empleadoOriginal.Nombre;
            txtApellidos.Text = empleadoOriginal.Apellidos;
            txtEdad.Text = empleadoOriginal.Edad.ToString();
            chkEsUsuario.IsChecked = empleadoOriginal.EsUsuario;
        }

        private void btnGuardar_Click(object sender, RoutedEventArgs e)
        {
            // Validaciones
            if (string.IsNullOrWhiteSpace(txtNombre.Text))
            {
                MessageBox.Show("El nombre es obligatorio.", "Validación",
                    MessageBoxButton.OK, MessageBoxImage.Warning);
                txtNombre.Focus();
                return;
            }

            if (string.IsNullOrWhiteSpace(txtApellidos.Text))
            {
                MessageBox.Show("Los apellidos son obligatorios.", "Validación",
                    MessageBoxButton.OK, MessageBoxImage.Warning);
                txtApellidos.Focus();
                return;
            }

            if (!int.TryParse(txtEdad.Text, out int edad) || edad <= 0 || edad > 120)
            {
                MessageBox.Show("Ingrese una edad válida (1-120).", "Validación",
                    MessageBoxButton.OK, MessageBoxImage.Warning);
                txtEdad.Focus();
                return;
            }

            // Actualizar en la base de datos
            ActualizarEmpleado(edad);
        }

        private void ActualizarEmpleado(int edad)
        {
            try
            {
                using (SqlConnection conexion = new SqlConnection(connectionString))
                {
                    conexion.Open();

                    string query = @"UPDATE Empleados 
                                    SET Nombre = @nombre, 
                                        Apellidos = @apellidos, 
                                        Edad = @edad, 
                                        EsUsuario = @esUsuario 
                                    WHERE Nombre = @nombreOriginal AND Apellidos = @apellidosOriginal";

                    using (SqlCommand comando = new SqlCommand(query, conexion))
                    {
                        comando.Parameters.AddWithValue("@nombre", txtNombre.Text);
                        comando.Parameters.AddWithValue("@apellidos", txtApellidos.Text);
                        comando.Parameters.AddWithValue("@edad", edad);
                        comando.Parameters.AddWithValue("@esUsuario", chkEsUsuario.IsChecked ?? false);
                        comando.Parameters.AddWithValue("@nombreOriginal", empleadoOriginal.Nombre);
                        comando.Parameters.AddWithValue("@apellidosOriginal", empleadoOriginal.Apellidos);

                        int filasAfectadas = comando.ExecuteNonQuery();

                        if (filasAfectadas > 0)
                        {
                            DialogResult = true;
                            Close();
                        }
                        else
                        {
                            MessageBox.Show("No se pudo actualizar el empleado.", "Error",
                                MessageBoxButton.OK, MessageBoxImage.Error);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al actualizar: {ex.Message}", "Error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void btnCancelar_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = false;
            Close();
        }



    }
}
