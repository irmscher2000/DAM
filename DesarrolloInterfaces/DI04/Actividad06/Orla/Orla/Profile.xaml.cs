using System;
using System.Collections.Generic;
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

namespace Orla
{
    /// <summary>
    /// Lógica de interacción para Profile.xaml
    /// </summary>
    public partial class Profile : Window
    {
        public Profile()
        {
            InitializeComponent();
        }

        public Profile(String Nombre, String Apellidos, String Email, String Foto)
        {
            InitializeComponent();
            Profile_Nombre.Text = Nombre;
            Profile_Apellidos.Text = Apellidos;
            Profile_Email.Text = Email;
            Profile_Image.Source = new BitmapImage(new Uri(Foto, UriKind.Relative));
        }
    }
}
