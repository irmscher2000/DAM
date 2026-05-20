using System.Windows;

namespace _1._8.NavegacionWPF
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

        private void Boton_Window1(object sender, RoutedEventArgs e)
        {
            Window1 AbrirVentana1 = new Window1();
            this.Close();
            AbrirVentana1.Show();
        }

        private void Boton_Window2(object sender, RoutedEventArgs e)
        {
            Window2 AbrirVentana2 = new Window2();
            this.Close();
            AbrirVentana2.Show();
        }

        private void Boton_Salir(object sender, RoutedEventArgs e)
        {
            System.Windows.Application.Current.Shutdown();
        }
    }
}
