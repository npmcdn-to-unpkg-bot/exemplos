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
    public partial class Feed : ContentPage
    {
        //private PostService service;
        private bool _curtido = false;

        public Feed()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();

            using (var service = new PostService())
            {
                lstPost.ItemsSource = service.ListarPosts().Result;
            }
            /*Post postFake = new Post();
            postFake.PostID = 0;
            postFake.Titulo = "Titulo";
            postFake.Texto = "Aqui vai ser inserido pelo administrador o texto correspondente a imagem que foi carregada para o post ou só o texto mesmo.";   


            List<Post> listaLocalDePost = new List<Post>();
            listaLocalDePost.Add(postFake);
            listaLocalDePost.Add(postFake);
            listaLocalDePost.Add(postFake);
            listaLocalDePost.Add(postFake);

            lstPost.ItemsSource = listaLocalDePost;*/
        }

        protected async void AtualizarPost(Object sender, EventArgs e)
        {
            using (var service = new PostService())
            {
                lstPost.ItemsSource = await service.ListarPosts();
                lstPost.EndRefresh();
            }            
        }

        protected void IrPostAberto(Object sender, EventArgs e)
        {
            var button = (Button)sender;

            Navigation.PushAsync(new View.PaginaPrincipal.PostAberto(Convert.ToInt32(button.CommandParameter)));
        }



    }
}
