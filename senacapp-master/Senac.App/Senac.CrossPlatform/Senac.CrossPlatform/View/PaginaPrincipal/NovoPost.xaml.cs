using Plugin.Media;
using Senac.CrossPlatform.Data;
using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;


using Xamarin.Forms;

namespace Senac.CrossPlatform.View.PaginaPrincipal


{
    public partial class NovoPost : ContentPage
    {
        private Stream _photoStream;
        static SenacLite database;
        

        public NovoPost()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();

			//Metodo para renderizar os componentes em diferentes dispositivos
			OnPlataformsComponents ();
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

        async void FotoPost(object sender, EventArgs e)
        {
           
            var action = await DisplayActionSheet("", "", "", "Galeria", "Tirar Foto");
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
                    if (file!=null && imgPost != null)
                    {
                        _photoStream = file.GetStream();
                        imgPost.Source = ImageSource.FromFile(file.Path);
                        addImagem.IsVisible = false;
                        alterarImagem.IsVisible = true;
                        removerImagem.IsVisible = true;

                    }
                    else
                    {
                        addImagem.IsVisible = true;
                        alterarImagem.IsVisible = false;
                        removerImagem.IsVisible = false;

                    }
                    
                }
               
            }
            if (action == "Galeria")
            {
                if (CrossMedia.Current.IsPickPhotoSupported)
                {

                    var photo = await CrossMedia.Current.PickPhotoAsync();
                    if (photo != null && imgPost != null)
                    {
                        _photoStream = photo.GetStream();
                        imgPost.Source = ImageSource.FromFile(photo.Path);
                        addImagem.IsVisible = false;
                        alterarImagem.IsVisible = true;
                        removerImagem.IsVisible = true;

                    }
                    else
                    {
                        addImagem.IsVisible = true;
                        alterarImagem.IsVisible = false;
                        removerImagem.IsVisible = false;

                    }

                }
                            
            }

        }
        async void ExcluirFoto(object sender, EventArgs e)
        {
            imgPost.Source = null;

            addImagem.IsVisible = true;
            alterarImagem.IsVisible = false;
            removerImagem.IsVisible = false;
        }

        async void OkAlerta(object sender, EventArgs e)
        {
            alertaPreencher.IsVisible = false;
            btnSalvar.IsVisible = true;
            normalPreenchimento.IsVisible = true;
            segundoAlertaPreencher.IsVisible = false;
        }
        async void OkPostado(object sender, EventArgs e)
        {
            alertaPostado.IsVisible = false;
            btnSalvar.IsVisible = true;



        }

        public void InserirPost(Object sender, EventArgs e)
        {
            using (var service = new PostService())
            {
                if (edtTitulo.Text == null || edtTexto.Text == null)
                {
                    //DisplayAlert("Postar", "Preencha todos os campos", "OK");
                    alertaPreencher.IsVisible = true;
                    btnSalvar.IsVisible = false;
                    normalPreenchimento.IsVisible = false;
                    segundoAlertaPreencher.IsVisible = true;

                }
                else
                {
                    var newPost = new Post();
                    newPost.Titulo = edtTitulo.Text;
                    newPost.Texto = edtTexto.Text;
                    
                    if (_photoStream != null)
                    {
                        newPost.Imagem = Helper.ConvertToBase64(_photoStream);
                    }

                    var tokens = Database.GetItems();
                    if (tokens.Any())
                    {
                        // SALVANDO NO POST O USUARIO
                        newPost.Usuario = new Usuario();
                        newPost.Usuario.UsuarioID = tokens.First().UsuarioID;
                    }

                    service.InserirPost(newPost);
                    //DisplayAlert("Postado", "Seu post foi efetuado", "OK");
                    alertaPostado.IsVisible = true;
                    btnSalvar.IsVisible = false;
                    edtTitulo.Text = null;
                    edtTexto.Text = null;
					imgPost.Source = null;


                }


            }
        }

		protected void OnPlataformsComponents()
		{
			//Botão add imagem
			Device.OnPlatform (
				iOS: () => addImagem.TextColor = Color.Blue,
				Android: () => addImagem.TextColor = Color.Red);
		}
    }
}
