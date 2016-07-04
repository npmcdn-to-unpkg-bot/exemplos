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
    public partial class PostAberto : ContentPage
    {
        private ComentarioService _service;
        private PostService _postService;
        private bool _curtido = false;
        private Post _post;
        static SenacLite database;


        public PostAberto()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
            _service = new ComentarioService();
            Comentarios();
            ComponentesGraficos();

        }

        public PostAberto(int postId) : this()
        {
            _postService = new PostService();
            _post = _postService.BuscarPost(postId).Result;
            txtTitulo.Text = _post.Titulo;
            txtTexto.Text = _post.Texto;
            //txtCidade.Text = _post.Cidade;
            imgPost.Source = _post.ImagemStream;
            lstComentario.ItemsSource = _service.ListarComentarios().Result.OrderByDescending(p => p.ComentarioID).ToList();
        }

        protected void IrFeed(Object sender, EventArgs e)
        {

            Navigation.PopAsync();

        }

        public void Curtir(Object sender, EventArgs e)
        {

            if (_curtido == false)
            {
                var inc = Convert.ToInt32(contLike.Text);
                inc++;
                var i = inc.ToString();
                contLike.Text = i;
                _curtido = true;
                btnLikeOff.IsVisible = false;
                btnLikeOn.IsVisible = true;
            }
            else
            {
                var inc = Convert.ToInt32(contLike.Text);
                inc--;
                var i = inc.ToString();
                contLike.Text = i;
                _curtido = false;
                btnLikeOff.IsVisible = true;
                btnLikeOn.IsVisible = false;
            }


        }

        public void Comentar(Object sender, EventArgs e)
        {
            var button = (Button)sender;
            Comentario c1 = new Comentario();

            // Salvando no comentario o POST e o USUARIO
            var tokens = Database.GetItems();
            if (tokens.Any())
            {
                c1.Usuario = new Usuario();
                c1.Usuario.UsuarioID =tokens.First().UsuarioID;
            }
            c1.Texto = newComentario.Text;
            c1.Post = _post;

            _service.InserirComentario(c1);
            lstComentario.ItemsSource = _service.ListarComentarios().Result.OrderByDescending(p => p.ComentarioID).ToList();
            newComentario.Text = "";
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

        public void Denunciar(Object sender, EventArgs e)
        {

        }

        public void Comentarios()
        {
            //Comentario comentario = new Comentario();
            //comentario.ComentarioID = 0;
            //comentario.Nome = "João";
            //comentario.Texto = "Que post massa hein, nossa!";

            //Comentario comentario2 = new Comentario();
            //comentario2.ComentarioID = 1;
            //comentario2.Nome = "José";
            //comentario2.Texto = "Fica na sua ai joao";

            //Comentario comentario3 = new Comentario();
            //comentario3.ComentarioID = 2;
            //comentario3.Nome = "João";
            //comentario3.Texto = "Demoro";

            //Comentario comentario4 = new Comentario();
            //comentario4.ComentarioID = 3;
            //comentario4.Nome = "Nathalia";
            //comentario4.Texto = "A vida é como um pai que bate na mãe";

            //List<Comentario> listaComentario = new List<Comentario>();
            //listaComentario.Add(comentario);
            //listaComentario.Add(comentario2);
            //listaComentario.Add(comentario3);
            //listaComentario.Add(comentario4);

            //lstComentario.ItemsSource = listaComentario;
            //contComent.Text = (listaComentario.Count.ToString());
            lstComentario.ItemsSource = _service.ListarComentarios().Result;
        }
        public void ComponentesGraficos()
        {
            //ftPerfil.Source = Device.OnPlatform(
            //    iOS: ImageSource.FromFile("Images/user.png"),
            //    Android: ImageSource.FromFile("user.png"),
            //    WinPhone: ImageSource.FromFile("Images/user.png"));

            //icLike.Source = Device.OnPlatform(
            //iOS: ImageSource.FromFile("Images/like.png"),
            //Android: ImageSource.FromFile("like.png"),
            //WinPhone: ImageSource.FromFile("Images/like.png"));

            //icComent.Source = Device.OnPlatform(
            //iOS: ImageSource.FromFile("Images/coment.png"),
            //Android: ImageSource.FromFile("coment.png"),
            //WinPhone: ImageSource.FromFile("Images/coment.png"));

            //icBlock.Source = Device.OnPlatform(
            //iOS: ImageSource.FromFile("Images/block.png"),
            //Android: ImageSource.FromFile("block.png"),
            //WinPhone: ImageSource.FromFile("Images/block.png"));

            //icSendComent.Source = Device.OnPlatform(
            //iOS: ImageSource.FromFile("Images/sent.png"),
            //Android: ImageSource.FromFile("sent.png"),
            //WinPhone: ImageSource.FromFile("Images/sent.png"));

            //icVoltar.Source = Device.OnPlatform(
            //iOS: ImageSource.FromFile("Images/back.png"),
            //Android: ImageSource.FromFile("back.png"),
            //WinPhone: ImageSource.FromFile("Images/back.png"));

            //imgPost.Source = Device.OnPlatform(
            //iOS: ImageSource.FromFile("Images/img2.jpg"),
            //Android: ImageSource.FromFile("img2.jpg"),
            //WinPhone: ImageSource.FromFile("Images/img2.png"));
        }

    }
}
