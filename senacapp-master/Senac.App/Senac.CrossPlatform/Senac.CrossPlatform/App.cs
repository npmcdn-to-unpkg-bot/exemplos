using Senac.CrossPlatform.Data;
using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.View.Curriculo;
using Senac.CrossPlatform.View.Login;
using Senac.CrossPlatform.View.PaginaPrincipal;
using SQLite;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace Senac.CrossPlatform
{
    public class App : Application
    {
        static SenacLite database;
        private UsuarioService _usuarioService;

        public App()
        {
            // The root page of your application
            _usuarioService = new UsuarioService();
			//comentario
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

        protected override void OnStart()
        {
            var tokens = Database.GetItems();
            if (!tokens.Any())
            {
                MainPage = new NavigationPage(new LoginInicial());
                //MainPage = new PaginaPrincipal();
            }
            else
            {
                var token = tokens.FirstOrDefault();
                //Chamo o usuário service para validar se o refresh token ainda é válido - ex: (urlbase)/Token
                var refreshToken = _usuarioService.AtualizarCredenciais(token);
                //Depois de gerado o token salvo aqui
                var tokenToSave = new Token();
                //setar todos os atributos
                tokenToSave.UsuarioID = token.UsuarioID;
                tokenToSave.TokenPrincipal = token.TokenPrincipal;
                tokenToSave.RefreshToken = refreshToken;

                Database.SaveItem(tokenToSave);

               MainPage = new PaginaPrincipal();
            }
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }
    }
}

