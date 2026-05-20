using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
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
    /// Lógica de interacción para BuscarEmpleado.xaml
    /// </summary>
    public partial class BuscarEmpleado : Window
    {

        private SqlConnection conexionConSql;
        private DataTable resultadosBusqueda;
        private Empleado empleadoSeleccionado;

        public BuscarEmpleado()
        {
            InitializeComponent();
            EstablecerConexion();
            resultadosBusqueda = new DataTable();
            
        }

        private void EstablecerConexion()
        {
            string CadenaDeConexion = ConfigurationManager.ConnectionStrings
                ["GestionEmpleados2026.Properties.Settings.GestionEmpleadosConnectionString"].ConnectionString;
            conexionConSql = new SqlConnection(CadenaDeConexion);
        }

        // Evento del botón Buscar
        private void btnBuscar_Click(object sender, RoutedEventArgs e)
        {
            string campo = (cmbCampoBusqueda.SelectedItem as ComboBoxItem)?.Content.ToString();
            string valorBusqueda = txtValorBusqueda.Text.Trim();

            if (string.IsNullOrEmpty(valorBusqueda))
            {
                MessageBox.Show("Por favor, ingrese un valor para buscar.", "Validación",
                    MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }

            BuscarEmpleados(campo, valorBusqueda);
        }

        // Método para buscar empleados
        private void BuscarEmpleados(string campo, string valor)
        {
            try
            {
                using (SqlConnection conexion = new SqlConnection(conexionConSql.ConnectionString))
                {
                    conexion.Open();

                    string query = "";

                    // Construir la consulta según el campo seleccionado
                    switch (campo)
                    {
                        case "Nombre":
                            query = "SELECT * FROM Empleados WHERE Nombre LIKE @valor";
                            break;
                        case "Apellidos":
                            query = "SELECT * FROM Empleados WHERE Apellidos LIKE @valor";
                            break;
                        case "Edad":
                            query = "SELECT * FROM Empleados WHERE Edad = @valor";
                            break;
                        case "EsUsuario":
                            bool valorBool = valor.ToLower() == "true" || valor.ToLower() == "sí" || valor == "1";
                            query = "SELECT * FROM Empleados WHERE EsUsuario = @valor";
                            valor = valorBool ? "1" : "0";
                            break;
                    }

                    using (SqlCommand comando = new SqlCommand(query, conexionConSql))
                    {
                        // Para búsquedas con LIKE (excepto edad y booleano)
                        if (campo == "Nombre" || campo == "Apellidos")
                        {
                            comando.Parameters.AddWithValue("@valor", "%" + valor + "%");
                        }
                        else
                        {
                            comando.Parameters.AddWithValue("@valor", valor);
                        }

                        SqlDataAdapter adaptador = new SqlDataAdapter(comando);

                        // Crear un nuevo DataTable en lugar de limpiar el existente
                        DataTable nuevoResultado = new DataTable();
                        adaptador.Fill(nuevoResultado);

                        // Reemplazar el DataTable anterior
                        resultadosBusqueda = nuevoResultado;

                        // Forzar la actualización del DataGrid
                        dgResultados.ItemsSource = null;  // Desconectar
                        dgResultados.ItemsSource = resultadosBusqueda.DefaultView;  // Reconectar
                    }
                }
                                
                // Mostrar resultados o mensaje
                if (resultadosBusqueda.Rows.Count > 0)
                {
                    dgResultados.Visibility = Visibility.Visible;
                    txtMensaje.Visibility = Visibility.Collapsed;
                    btnModificar.IsEnabled = true;

                    MessageBox.Show($"Se encontraron {resultadosBusqueda.Rows.Count} empleado(s).",
                        "Resultados", MessageBoxButton.OK, MessageBoxImage.Information);
                }
                else
                {
                    dgResultados.Visibility = Visibility.Hidden;
                    txtMensaje.Visibility = Visibility.Visible;
                    btnModificar.IsEnabled = false;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al buscar empleados: {ex.Message}", "Error",
                    MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        // Evento para modificar el empleado seleccionado
        private void btnModificar_Click(object sender, RoutedEventArgs e)
        {
            if (dgResultados.SelectedItem != null)
            {
                DataRowView filaSeleccionada = (DataRowView)dgResultados.SelectedItem;

                // Crear objeto Empleado con los datos seleccionados
                Empleado empleado = new Empleado
                {
                    Nombre = filaSeleccionada["Nombre"].ToString(),
                    Apellidos = filaSeleccionada["Apellidos"].ToString(),
                    Edad = Convert.ToInt32(filaSeleccionada["Edad"]),
                    EsUsuario = Convert.ToBoolean(filaSeleccionada["EsUsuario"])
                };

                // Abrir ventana de modificación
                ModificarEmpleadoWindow ventanaModificar = new ModificarEmpleadoWindow(empleado, conexionConSql.ConnectionString);
                ventanaModificar.Owner = this;

                if (ventanaModificar.ShowDialog() == true)
                {
                    // Si se modificó, refrescar la búsqueda
                    btnBuscar_Click(sender, e);
                    MessageBox.Show("Empleado actualizado correctamente.", "Éxito",
                        MessageBoxButton.OK, MessageBoxImage.Information);
                }
            }
            else
            {
                MessageBox.Show("Por favor, seleccione un empleado para modificar.", "Validación",
                    MessageBoxButton.OK, MessageBoxImage.Warning);
            }
        }

        // Cerrar ventana
        private void btnCerrar_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }



    }
}
