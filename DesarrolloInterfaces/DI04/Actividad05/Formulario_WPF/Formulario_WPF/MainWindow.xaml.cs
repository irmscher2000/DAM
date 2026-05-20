using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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
using Microsoft.Reporting.WinForms;

namespace Formulario_WPF
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        // Coleccion observable que alimenta el DataGrid
        private ObservableCollection<Empleado> listaEmpleados = new ObservableCollection<Empleado>();

        public MainWindow()
        {
            InitializeComponent();

            dgEmpleados.ItemsSource = listaEmpleados;
        }

        // Cargar Foto
        private void btnCargarFoto_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog()
            {
                Filter = "Imágenes|*.jpg;*.jpeg;*.png;*.gif",
                InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyPictures)
            };

            if (ofd.ShowDialog() == true)
            {
                BitmapImage bitmap = new BitmapImage(new Uri(ofd.FileName));
                imgFoto.Source = bitmap;
            }
        }


        private void Txt_GotFocus(object sender, RoutedEventArgs e)
        {
            if (sender is TextBox textBox)
            {
                if (textBox.Text == "Dirección" || textBox.Text == "Ciudad" || textBox.Text == "Provincia" || textBox.Text == "Código Postal" || textBox.Text == "País")
                {
                    textBox.Text = "";
                }
            }
        }

        private void Txt_LostFocus(object sender, RoutedEventArgs e)
        {
            if (sender is TextBox textBox)
            {
                if (string.IsNullOrWhiteSpace(textBox.Text))
                {
                    if (textBox.Name == "txtDireccion")
                        textBox.Text = "Dirección";
                    else if (textBox.Name == "txtCiudad")
                        textBox.Text = "Ciudad";
                    else if (textBox.Name == "txtProvincia")
                        textBox.Text = "Provincia";
                    else if (textBox.Name == "txtCodigoPostal")
                        textBox.Text = "Código Postal";
                    else if (textBox.Name == "txtPais")
                        textBox.Text = "País";
                }
            }
        }


        // Boton para Guardar
        private void btnGuardar_Click(object sender, RoutedEventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtNombre.Text))
            {
                MessageBox.Show("El campo Nombre es obligatorio.", "Validación", MessageBoxButton.OK, MessageBoxImage.Warning);
                txtNombre.Focus();
                return;
            }

            if (string.IsNullOrWhiteSpace(txtApellido.Text))
            {
                MessageBox.Show("El campo Apellido es obligatorio.", "Validación", MessageBoxButton.OK, MessageBoxImage.Warning);
                txtApellido.Focus();
                return;
            }

            if (string.IsNullOrWhiteSpace(txtDNI.Text))
            {
                MessageBox.Show("El campo DNI es obligatorio", "Validacion", MessageBoxButton.OK, MessageBoxImage.Warning);
                txtDNI.Focus();
                return;
            }

            if (string.IsNullOrWhiteSpace(txtEmail.Text))
            {
                MessageBox.Show("El campo Email es obligatorio.", "Validación", MessageBoxButton.OK, MessageBoxImage.Warning);
                txtEmail.Focus();
                return;
            }

            if (string.IsNullOrWhiteSpace(txtTelefono.Text))
            {
                MessageBox.Show("El campo Teléfono es obligatorio.", "Validación", MessageBoxButton.OK, MessageBoxImage.Warning);
                txtTelefono.Focus();
                return;
            }

            // Se añaden a DataGrid
            listaEmpleados.Add(new Empleado
            {
                Nombre = txtNombre.Text.Trim(),
                Apellido = txtApellido.Text.Trim(),
                DNI = txtDNI.Text.Trim(),
                Email = txtEmail.Text.Trim(),
                Telefono = txtTelefono.Text.Trim(),
            });

            LimpiarFormulario();
            MessageBox.Show("Empleado guardado correctamente.", "Información", MessageBoxButton.OK, MessageBoxImage.Information);
        }

        // Boton para cancelar
        private void btnCancelar_Click(object sender, EventArgs e)
        {
            MainWindow nuevaVentana = new MainWindow();

            // Conservo la lista
            nuevaVentana.listaEmpleados = listaEmpleados;
            nuevaVentana.dgEmpleados.ItemsSource = listaEmpleados;
            nuevaVentana.Show();
            this.Close();
        }

        // Boton para generar Informe
        private void btnGenerarInforme_Click(object sender, RoutedEventArgs e)
        {
            reportViewer.LocalReport.ReportPath = "InformeEmpleados.rdlc";

            ReportDataSource rds = new ReportDataSource("DataSet1", listaEmpleados);

            reportViewer.LocalReport.DataSources.Clear();
            reportViewer.LocalReport.DataSources.Add(rds);

            reportViewer.RefreshReport();
        }

        // Limpiar Forumulario
        private void LimpiarFormulario()
        {
            txtNombre.Text = "";
            txtApellido.Text = "";
            txtEmail.Text = "";
            txtTelefono.Text = "";
            dpFechaNacimiento.SelectedDate = null;
            txtDNI.Text = "";
            imgFoto.Source = null;
            txtDireccion.Text = "Dirección";
            txtCiudad.Text = "Ciudad";
            txtProvincia.Text = "Provincia";
            txtCodigoPostal.Text = "Código Posta";
            txtPais.Text = "País";
            txtRedSocial.Text = "";
            cbRol.SelectedIndex = -1;
            txtDescripcion.Text = "";
        }
    }

    // Modelo de datos
    public class Empleado
    {
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public string DNI { get; set; }
        public string Email { get; set; }
        public string Telefono { get; set; }

    }
}