using System.Windows;

namespace Menu_Desplegable_Navegacion
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

        private void M_Nuevo_Click(object sender, RoutedEventArgs e)
        {
            MainWindow NuevaVentana = new MainWindow();
            NuevaVentana.Show();
        }

        private void M_Abrir_Click(object sender, RoutedEventArgs e)
        {
            Microsoft.Win32.OpenFileDialog AbrirFichero = new Microsoft.Win32.OpenFileDialog();
            AbrirFichero.ShowDialog();
        }

        private void M_Guardar_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(" Guardado ");
        }

        private void M_Guardar_como_Click(object sender, RoutedEventArgs e)
        {
            Microsoft.Win32.SaveFileDialog GuardarFicheroComo = new Microsoft.Win32.SaveFileDialog();
            GuardarFicheroComo.ShowDialog();
        }

        private void M_Imprimir_Click(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.PrintDialog Imprimir = new System.Windows.Controls.PrintDialog();
            Imprimir.ShowDialog();
        }

        private void M_Salir_Click(object sender, RoutedEventArgs e)
        {
            System.Windows.Application.Current.Shutdown();
        }

        private void M_Usuarios_Click(object sender, RoutedEventArgs e)
        {
            Usuarios AbrirUsuarios = new Usuarios();
            AbrirUsuarios.Show();
        }
    }
}
