namespace Senac.Data.Migrations
{
    using Model;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using System.Security.Cryptography;
    using System.Text;
    internal sealed class Configuration : DbMigrationsConfiguration<SenacDB>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(SenacDB context)
        {
            var db = new SenacDB();
            var client = new Client();

            client.ClientName = "senacapp";
            client.Secret = "1234567";
            client.Name = "Aplicativo";
            client.ApplicationType = 0;
            client.Active = true;
            client.RefreshTokenLifeTime = 10080;
            client.AllowedOrigin = "*";

            db.Clients.Add(client);


            var usuario = new Usuario();
            HashAlgorithm hashAlgorithm = new SHA256CryptoServiceProvider();
            byte[] byteValue = Encoding.UTF8.GetBytes("321321");
            byte[] byteHash = hashAlgorithm.ComputeHash(byteValue);
            usuario.Nome = "Elton Naoki Iwakura";
            usuario.Cpf = "02227753196";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);


            var usuario1 = new Usuario();
            usuario.Nome = "Antônio Cézar de Souza Santana";
            usuario.Cpf = "05971203150";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);


            var usuario2 = new Usuario();
            usuario.Nome = "Wendell Ugalds Godoy";
            usuario.Cpf = "03783853133";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);


            var usuario3 = new Usuario();
            usuario.Nome = "Walisson Felipe";
            usuario.Cpf = "04228474101";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);


            var usuario4 = new Usuario();
            usuario.Nome = "Nathália Lima";
            usuario.Cpf = "00765697211";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);

            var usuario5 = new Usuario();
            usuario.Nome = "Gessé Rocha";
            usuario.Cpf = "05320851162";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);

            var usuario6 = new Usuario();
            usuario.Nome = "Felipe Bilhares";
            usuario.Cpf = "05721985151";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);


            var usuario7 = new Usuario();
            usuario.Nome = "Andrews Filipe";
            usuario.Cpf = "04788912104";
            usuario.Senha = Convert.ToBase64String(byteHash);
            db.Usuarios.Add(usuario);


            db.SaveChanges();
        }
    }
}