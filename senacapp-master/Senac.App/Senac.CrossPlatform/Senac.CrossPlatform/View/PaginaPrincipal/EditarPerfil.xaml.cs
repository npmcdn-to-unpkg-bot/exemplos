using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

namespace Senac.CrossPlatform.View.PaginaPrincipal
{
    public partial class EditarPerfil : ContentPage
    {
        public EditarPerfil()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
        }
        public EditarPerfil(ViewModel.Perfil perfil):this()
        {

            edtNome.Text = perfil.Nome;
            edtBairro.Text = perfil.Bairro;
            edtCidade.Text = perfil.Cidade;
            edtCurriculum.Text = perfil.Curriculum;
            edtEmail.Text = perfil.Email;
            edtRua.Text = perfil.Rua;
            edtTel.Text = perfil.Telefone;
            edtIdade.Text = perfil.Idade;
        }

        protected void Cancelar(Object sender, EventArgs e)
        {

            Navigation.PopAsync();

        }


        public void AtualizarPerfil(Object sender, EventArgs e)
         {
             int idade = Convert.ToInt32(edtIdade.Text);
             if (edtNome.Text == null || edtNome.Equals(""))
             {
                 lblErroNome.IsVisible = true;
             }
             else if (edtTel.Text == null || edtTel.Equals(""))
             {
                 lblErroTel.IsVisible = true;
             }
             else if (edtEmail.Text == null || edtEmail.Equals(""))
             {
                 lblErroEmail.IsVisible = true;
             }
             else if (edtRua.Text == null || edtRua.Equals(""))
             {
                lblErroRua.IsVisible = true;
             }
             else if (edtBairro.Text == null || edtBairro.Equals(""))
             {
                 lblErroBairro.IsVisible = true;
             }
             else if (edtCidade.Text == null || edtCidade.Equals(""))
             {
                 lblErroCidade.IsVisible = true;
             }
             else if (idade > 70)
             {
                 lblErroIdade.IsVisible = true;
             }
             else if (edtIdade.Text == null || edtIdade.Equals(""))
             {
                 //lblErroSobreMim.IsVisible = true;
             }
             else if (edtCurriculum.Text == null || edtCurriculum.Equals(""))
             {
                 lblErroCurriculo.IsVisible = true;
             }
             else
             {
                //Navigation.PushAsync(new Perfil());
                Navigation.PopAsync();
            }

         }

    }
}
