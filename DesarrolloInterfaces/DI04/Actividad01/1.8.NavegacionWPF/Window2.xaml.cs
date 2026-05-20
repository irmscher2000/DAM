using System.Windows;

namespace _1._8.NavegacionWPF
{
    /// <summary>
    /// Lógica de interacción para Window2.xaml
    /// </summary>
    public partial class Window2 : Window
    {
        public Window2()
        {
            InitializeComponent();
        }

        private void Mainwindow(object sender, RoutedEventArgs e)
        {
            MainWindow AbrirMainWindow = new MainWindow();
            this.Close();
            AbrirMainWindow.Show();
        }

        private void Boton2_AbrirPagina(object sender, RoutedEventArgs e)
        {
            MyFrame.NavigationService.Navigate(new Page1());
        }
    }
}
