namespace Senac.Data
{
    using Interface;
    using Model;
    using System;
    using System.Data.Entity;
    using System.Linq;

    public class SenacDB : DbContext, ISenacDB
    {   
        // Your context has been configured to use a 'SenacDB' connection string from your application's 
        // configuration file (App.config or Web.config). By default, this connection string targets the 
        // 'Senac.Data.SenacDB' database on your LocalDb instance. 
        // 
        // If you wish to target a different database and/or database provider, modify the 'SenacDB' 
        // connection string in the application configuration file.
        public SenacDB()
            : base("name=SenacDB")
        {
            Database.SetInitializer(new CreateDatabaseIfNotExists<SenacDB>());
        }

        // Add a DbSet for each entity type that you want to include in your model. For more information 
        // on configuring and using a Code First model, see http://go.microsoft.com/fwlink/?LinkId=390109.

        public virtual DbSet<Usuario> Usuarios { get; set; }
        public virtual DbSet<Post> Post { get; set; }
        public virtual DbSet<Comentario> Comentarios { get; set; }
        public virtual DbSet<Client> Clients { get; set; }
        public virtual DbSet<RefreshToken> RefreshTokens { get; set; }
        public virtual DbSet<Curriculo> Curriculo { get; set; }

        public override int SaveChanges()
        {
            return base.SaveChanges();
        }
    }

    //public class MyEntity
    //{
    //    public int Id { get; set; }
    //    public string Name { get; set; }
    //}
}