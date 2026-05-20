using System.Windows;

namespace _1._8.NavegacionWPF
{
    /// <summary>
    /// Lógica de interacción para Window1.xaml
    /// </summary>
    public partial class Window1 : Window
    {
        public Window1()
        {
            InitializeComponent();
        }

        private void Mainwindow(object sender, RoutedEventArgs e)
        {
            // Abrir la ventana principal y cerrar esta ventana
            MainWindow AbrirMainWindow = new MainWindow();
            this.Close();
            AbrirMainWindow.Show();
        }
    }
}
