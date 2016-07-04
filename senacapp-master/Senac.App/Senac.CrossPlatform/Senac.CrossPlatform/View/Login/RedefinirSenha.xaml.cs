using Senac.CrossPlatform.Data;
using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Senac.CrossPlatform.View.Login
{
    public partial class RedefinirSenha : ContentPage
    {
        private UsuarioService _usuarioService;
        private static SenacLite _database;

        public RedefinirSenha()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
            _database = new SenacLite();
            _usuarioService = new UsuarioService();
            
        }

        public void ValidarSenha(Object sender, EventArgs e)
        {
            var button = (Button)sender;
            button.IsEnabled = false;

            if (txtNovaSenha.Text == txtRepetirNovaSenha.Text)
            {
                if (string.IsNullOrEmpty(txtNovaSenha.Text))
                {
                    DisplayAlert("Senha Inválida", "Campo vazio, insira uma nova senha", "Ok");
                }
                else
                {
                           
                    var usuario = new Usuario();
                    usuario.UsuarioID = _database.GetItems().FirstOrDefault().UsuarioID;
                    usuario.Senha = txtNovaSenha.Text;

                    _usuarioService.AtualizarUsuario(usuario);

                    Application.Current.MainPage = new NavigationPage(new Login.LoginInicial());
                  
                    DisplayAlert("Aviso", "As senhas foram alteradas com sucesso!", "Ok");

                }
            }
            else if (string.IsNullOrEmpty(txtNovaSenha.Text) || string.IsNullOrEmpty(txtRepetirNovaSenha.Text))
            {
                DisplayAlert("Senha Inválida", "Campo vazio, insira uma nova senha", "Ok");
                //Quando estiver vazio ou nulo
            }
            else
            {
                DisplayAlert("Senha invalida", "Senhas Diferentes", "Ok");
            }

            button.IsEnabled = true;
        }

        protected void VoltarLogin(Object sender, EventArgs e)
        {

            Navigation.PopAsync();

        }


        void LimitaCaracteres(object sender, EventArgs e)
        {
            Entry entry = sender as Entry;
            String val = entry.Text; //Obter texto atual

            if (val.Length > 10)  //Se é mais do que a sua restrição de caráter
            {
                val = val.Remove(val.Length - 1);  // Retirar último caractere
                entry.Text = val;  //Defina o valor Velho
            }
        }

    }
}
