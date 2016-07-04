using Senac.CrossPlatform.Data;
using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

namespace Senac.CrossPlatform.View.Login
{
    public partial class LoginPrincipal : ContentPage
    {
        private UsuarioService _usuarioService;
        private const string _mascara = "###.###.###-##";
        private string _oldTypedCpf = "";
        private bool _isUpdating;
        static SenacLite database;
        private string cpfLoginPrincipal;
        private Usuario _usuario;


        public LoginPrincipal()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
            database = new SenacLite();
            _usuarioService = new UsuarioService();
            imgSenac.Source = Device.OnPlatform(
                iOS: ImageSource.FromFile("Images/senac_splash.png"),
                Android: ImageSource.FromFile("senac_splash.png"),
                WinPhone: ImageSource.FromFile("Images/icone_senac.png"));
        }

        public LoginPrincipal(Usuario usuario) : this()
        {
            txtCpf.Text = usuario.Cpf;
            _usuario = usuario;
        }

        public void ValidarCpf(Object sender, EventArgs e)
        {
            var entry = (Entry)sender;

            if (string.IsNullOrEmpty(txtCpf.Text))
            {
                return;
            }

            string str = Helper.removeMascara(entry.Text);
            string mascaraAplicada = "";

            if (_isUpdating)
            {
                _oldTypedCpf = str;
                _isUpdating = false;
                return;
            }

            int i = 0;
            int j = 0;

            foreach (var m in _mascara.ToCharArray())
            {
                j++;
                if (m != '#' && str.Length > _oldTypedCpf.Length)
                {
                    mascaraAplicada += m;
                    continue;
                }
                try
                {
                    mascaraAplicada += str[i];
                }
                catch (Exception)
                {
                    break;
                }
                i++;
            }
            _isUpdating = true;
            entry.Text = mascaraAplicada;
        }
        public static SenacLite Database
        {
            get
            {
                if (database == null)
                {
                    database = new SenacLite();
                }
                return database;
            }
        }

        public async void AutenticarUsuario(Object sender, EventArgs e)
        {
            var button = (Button)sender;
            button.IsEnabled = false;
            indicador.IsRunning = true;
            btnEntrar.IsVisible = false;
            txtCarregando.IsVisible = true;

            if (string.IsNullOrEmpty(txtCpf.Text) || string.IsNullOrEmpty(txtSenha.Text))
            {
                //await DisplayAlert("Aviso!", "Campo obrigatório", "Ok");
                lblErro.IsVisible = true;
                button.IsEnabled = true;
                return;
            }

            if (!Helper.validarCpf(Helper.removeMascara(txtCpf.Text)))
            {
                //await DisplayAlert("Aviso!", "Seu CPF não é válido", "Ok");
                erroInvalido.IsVisible = true;
                button.IsEnabled = true;
                return;
            }



            var usuario = await _usuarioService.AutenticarUsuario(Helper.removeMascara(txtCpf.Text), txtSenha.Text);

            if (usuario.StatusCode == HttpStatusCode.BadRequest)
            {
                await DisplayAlert("Aviso", "Nome de usuário ou senha estão incorretos!", "OK");
            }
            else if (usuario.StatusCode == HttpStatusCode.OK)
            {
                Token t = new Token();
                // TODO: Fazer 't' receber os dados que retornam de 'usuario'
                t.RefreshToken = usuario.refresh_token;
                t.TokenPrincipal = usuario.access_token;

                // SALVA ID DO USUARIO
                t.UsuarioID = _usuario.UsuarioID;

                Database.SaveItem(t);

                button.IsEnabled = true;
                indicador.IsRunning = false;
                btnEntrar.IsVisible = true;
                txtCarregando.IsVisible = false;
                erroInvalido.IsVisible = false;
                lblErro.IsVisible = false;
                Application.Current.MainPage = new PaginaPrincipal.PaginaPrincipal();
            }
            else
            {
                await DisplayAlert("Aviso", "Ocorreu um erro no servidor, tente novamente mais tarde.", "OK");
            }

            button.IsEnabled = true;
            indicador.IsRunning = false;
            btnEntrar.IsVisible = true;
            txtCarregando.IsVisible = false;
        }


        public void AlterarSenha(Object sender, EventArgs e)
        {
            Navigation.PushAsync(new View.Login.RedefinirSenha());
        }

    }
}
