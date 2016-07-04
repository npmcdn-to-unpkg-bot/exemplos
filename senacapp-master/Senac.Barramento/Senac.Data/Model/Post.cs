using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Model
{
    public class Post
    {
        public int PostID { get; set; }
        public Usuario Usuario { get; set; }

        public String Imagem { get; set; }
        public String Titulo { get; set; }
        public String Texto { get; set; }
        public String Cidade { get; set; }
        public String Unidade { get; set; }
        public String Area { get; set; }

        public List<Comentario> Comentarios { get; set; }
    }
}
