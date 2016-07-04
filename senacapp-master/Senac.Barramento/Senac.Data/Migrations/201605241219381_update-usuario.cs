namespace Senac.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class updateusuario : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Clients",
                c => new
                    {
                        ClientID = c.Int(nullable: false, identity: true),
                        ClientName = c.String(),
                        Secret = c.String(),
                        Name = c.String(),
                        ApplicationType = c.Int(nullable: false),
                        Active = c.Boolean(nullable: false),
                        RefreshTokenLifeTime = c.Int(nullable: false),
                        AllowedOrigin = c.String(),
                    })
                .PrimaryKey(t => t.ClientID);
            
            CreateTable(
                "dbo.Comentarios",
                c => new
                    {
                        ComentarioId = c.Int(nullable: false, identity: true),
                        Texto = c.String(),
                        Post_PostID = c.Int(),
                        Usuario_UsuarioID = c.Int(),
                    })
                .PrimaryKey(t => t.ComentarioId)
                .ForeignKey("dbo.Posts", t => t.Post_PostID)
                .ForeignKey("dbo.Usuarios", t => t.Usuario_UsuarioID)
                .Index(t => t.Post_PostID)
                .Index(t => t.Usuario_UsuarioID);
            
            CreateTable(
                "dbo.Posts",
                c => new
                    {
                        PostID = c.Int(nullable: false, identity: true),
                        Imagem = c.String(),
                        Titulo = c.String(),
                        Texto = c.String(),
                        Cidade = c.String(),
                        Unidade = c.String(),
                        Area = c.String(),
                        Usuario_UsuarioID = c.Int(),
                    })
                .PrimaryKey(t => t.PostID)
                .ForeignKey("dbo.Usuarios", t => t.Usuario_UsuarioID)
                .Index(t => t.Usuario_UsuarioID);
            
            CreateTable(
                "dbo.Usuarios",
                c => new
                    {
                        UsuarioID = c.Int(nullable: false, identity: true),
                        Nome = c.String(),
                        Cpf = c.String(),
                        Senha = c.String(),
                    })
                .PrimaryKey(t => t.UsuarioID);
            
            CreateTable(
                "dbo.Curriculoes",
                c => new
                    {
                        CurriculoID = c.Int(nullable: false, identity: true),
                        Nome = c.String(),
                        Telefone = c.Int(nullable: false),
                        Celular = c.Int(nullable: false),
                        Empresa = c.String(),
                        Funcao = c.String(),
                        Curso = c.String(),
                        NMatricula = c.Long(nullable: false),
                        TerminoCurso = c.String(),
                        DescricaoCurso = c.String(),
                    })
                .PrimaryKey(t => t.CurriculoID);
            
            CreateTable(
                "dbo.RefreshTokens",
                c => new
                    {
                        RefreshTokenID = c.String(nullable: false, maxLength: 128),
                        Subject = c.String(nullable: false, maxLength: 50),
                        ClientId = c.String(nullable: false, maxLength: 50),
                        IssuedUtc = c.DateTime(nullable: false),
                        ExpiresUtc = c.DateTime(nullable: false),
                        ProtectedTicket = c.String(nullable: false),
                    })
                .PrimaryKey(t => t.RefreshTokenID);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Comentarios", "Usuario_UsuarioID", "dbo.Usuarios");
            DropForeignKey("dbo.Posts", "Usuario_UsuarioID", "dbo.Usuarios");
            DropForeignKey("dbo.Comentarios", "Post_PostID", "dbo.Posts");
            DropIndex("dbo.Posts", new[] { "Usuario_UsuarioID" });
            DropIndex("dbo.Comentarios", new[] { "Usuario_UsuarioID" });
            DropIndex("dbo.Comentarios", new[] { "Post_PostID" });
            DropTable("dbo.RefreshTokens");
            DropTable("dbo.Curriculoes");
            DropTable("dbo.Usuarios");
            DropTable("dbo.Posts");
            DropTable("dbo.Comentarios");
            DropTable("dbo.Clients");
        }
    }
}
