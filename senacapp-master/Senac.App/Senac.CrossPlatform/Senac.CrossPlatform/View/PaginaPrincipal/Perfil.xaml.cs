using Plugin.Media;
using Senac.CrossPlatform.Data;
using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

namespace Senac.CrossPlatform.View.PaginaPrincipal
{
    public partial class Perfil : ContentPage
    {
        static SenacLite database;
        private PostService service;
        public Perfil()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            database = new SenacLite();
            service = new PostService();
            InitializeComponent();
           // lstMeusPosts.ItemsSource = service.ListarPosts().Result;
        }
        protected override void OnAppearing()
        {
           //  lstMeusPosts.ItemsSource = service.ListarPosts().Result;
        }
        public void EditarPerfil(Object sender, EventArgs e)
        {
            var perfil = new ViewModel.Perfil();
            perfil.Nome = lblNome.Text;
            perfil.Bairro = lblBairro.Text;
            perfil.Cidade = lblCidade.Text;
            perfil.Curriculum = lblCurriculo.Text;
            perfil.Email = lblEmail.Text;
            perfil.Idade = lblIdade.Text;
            perfil.Rua = lblRua.Text;
            perfil.Telefone = lblTel.Text;
            
                   
            Navigation.PushAsync(new EditarPerfil(perfil));
        }

       
        public async void DeletarPost(Object sender, EventArgs e)
        {
            var post = new Post();
            var postId = post.PostID;
            await service.ExcluirPost(postId);
        }

        public void RedefinirSenha(Object sender, EventArgs e)
        {
            Navigation.PushAsync(new View.Login.RedefinirSenha());
        }

        public async void Sair(Object sender, EventArgs e)
        {
            var resposta = await DisplayAlert("Aviso", "Deseja realmente sair do aplicativo?", "Sim", "Não");

            if(resposta)
            {
                database.DeleteAll();
                Application.Current.MainPage = new NavigationPage(new Login.LoginInicial());
            }
        }

        async void AtualizarFoto(object sender, EventArgs e)
        {
            var action = await DisplayActionSheet("Foto do Perfil", "", "", "Galeria", "Tirar Foto");
            if (action == "Tirar Foto")
            {
                if (CrossMedia.Current.IsCameraAvailable && CrossMedia.Current.IsTakePhotoSupported)
                {
                    var mediaOptions = new Plugin.Media.Abstractions.StoreCameraMediaOptions
                    {
                        Directory = "Receipts",
                        Name = $"{DateTime.UtcNow}.jpg"
                    };
                    var file = await CrossMedia.Current.TakePhotoAsync(mediaOptions);
                    imgCamera.Source = ImageSource.FromFile(file.Path);
                }
            }
            if (action == "Galeria")
            {
                if (CrossMedia.Current.IsPickPhotoSupported)
                {
                    var photo = await CrossMedia.Current.PickPhotoAsync();
                    imgCamera.Source = ImageSource.FromFile(photo.Path);

                }
            }
        }
    }

}
