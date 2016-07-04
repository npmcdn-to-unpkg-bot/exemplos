using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

//CPF para login: 215.718.546-14 ----------------------------------------------------------------------

namespace Senac.CrossPlatform.View.Login
{
    public partial class LoginInicial : ContentPage
    {
        private UsuarioService _usuarioService;
        private const string _mascara = "###.###.###-##";
        private string _oldTypedCpf;
        private bool _isUpdating;

        public LoginInicial()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            _usuarioService = new UsuarioService();
            InitializeComponent();
            //lblErro.IsVisible = false;
            //lblProximo.IsVisible = false;
            imgSenac.Source = Device.OnPlatform(
                iOS: ImageSource.FromFile("Images/senac_splash.png"),
                Android: ImageSource.FromFile("senac_splash.png"),
                WinPhone: ImageSource.FromFile("Images/icone_senac.png"));

            texto.Text = DependencyService.Get<IDevice>().GetIndentifier();// +" "+ DependencyService.Get<IDevice>().GetLocation().Result;

        }

        public void ValidarCpf(Object sender, EventArgs e)
        {
            var entry = (Entry)sender;
            if (string.IsNullOrEmpty(entry.Text))
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

        public async void ProcurarUsuario(Object sender, EventArgs e)
        {
            var button = (Button)sender;
            button.IsEnabled = false;

            if (string.IsNullOrEmpty(txtCpf.Text))
            {
                //await DisplayAlert("Aviso!", "CPF não pode ser branco!", "Ok");
                erroBranco.IsVisible = true;
                erroInvalido.IsVisible = false;
                lblErro.IsVisible = false;
                button.IsEnabled = true;
                return;
            }


            if (!Helper.validarCpf(Helper.removeMascara(txtCpf.Text)))
            {
                //await DisplayAlert("Aviso!", "Seu CPF não é válido", "Ok");
                button.IsEnabled = true;
                erroInvalido.IsVisible = true;
                erroBranco.IsVisible = false;
                lblErro.IsVisible = false;
                return;
            }

            indicador.IsRunning = true;
            btnAvancar.IsVisible = false;
            txtCarregando.IsVisible = true;

            Usuario usuario = await _usuarioService.ProcurarUsuario(txtCpf.Text);
            if (usuario == null)
            {
                lblErro.IsVisible = true;
                erroInvalido.IsVisible = false;
                erroBranco.IsVisible = false;
            }
            else
            {
                if(string.IsNullOrEmpty(usuario.Senha))
                {
                    await Navigation.PushAsync(new CadastrarSenha(usuario));
                }
                else
                {
                    lblErro.IsVisible = false;
                    await Navigation.PushAsync(new LoginPrincipal(usuario));
                }
            }

            indicador.IsRunning = false;
            btnAvancar.IsVisible = true;
            txtCarregando.IsVisible = false;
            button.IsEnabled = true;
        }

        public bool VerificarCpf(string cpf)
        {
            return false;
        }

        public void RedirecionaSite(Object sender,EventArgs e)
        {
            Device.OpenUri(new Uri("http://www.ms.senac.br/"));
        }
    }

}



