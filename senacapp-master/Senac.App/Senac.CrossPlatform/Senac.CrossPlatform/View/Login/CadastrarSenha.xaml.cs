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

    public partial class CadastrarSenha : ContentPage
    {
        private UsuarioService _usuarioService;
        private static SenacLite _database;
        private Usuario _usuario;

        public CadastrarSenha()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
            _database = new SenacLite();
            _usuarioService = new UsuarioService();

            imgSenac.Source = Device.OnPlatform(
            iOS: ImageSource.FromFile("Images/icone_senac.png"),
            Android: ImageSource.FromFile("icone_senac.png"),
            WinPhone: ImageSource.FromFile("Images/icone_senac.png"));
        }

        public CadastrarSenha(Usuario usuario) : this()
        {
            _usuario = usuario;
        }

        public async void ValidarSenha(Object sender, EventArgs e)
        {
            var button = (Button)sender;
            button.IsEnabled = false;

            if (txtRetryNovaSenha.Text == txtCadastrarSenha.Text)
            {
                if (string.IsNullOrEmpty(txtCadastrarSenha.Text))
                {
                    await DisplayAlert("Senha Inválida", "Campo vazio, insira uma nova senha", "Ok");
                }
                else
                {

                    var usuario = new Usuario();
                    usuario.UsuarioID = _usuario.UsuarioID;
                    usuario.Nome = _usuario.Nome;
                    usuario.Cpf = _usuario.Cpf;
                    usuario.Senha = txtCadastrarSenha.Text;

                    var resposta = await _usuarioService.AtualizarUsuario(usuario);

                    if(resposta)
                    {
                        DisplayAlert("Aviso", "As senhas foram gravadas com sucesso!", "Ok");
                        Navigation.PushAsync(new LoginPrincipal());
                    }
                    else
                    {
                        await DisplayAlert("Aviso", "Ocorreu um problema com o servidor", "Ok");
                    }
                }
            }

            else if (string.IsNullOrEmpty(txtCadastrarSenha.Text) || string.IsNullOrEmpty(txtRetryNovaSenha.Text))
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

        // Metodo para limitar senha

        public void LimitarCaracter(object sender, EventArgs e)
        {
            Entry entry = sender as Entry;
            String val = entry.Text;

            if (val.Length > 10)
            {
                val = val.Remove(val.Length - 1);
                entry.Text = val;
            }
        }
    }
}