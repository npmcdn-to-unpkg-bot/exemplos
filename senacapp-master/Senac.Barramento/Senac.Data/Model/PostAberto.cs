using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Model
{
    public class PostAberto
    {
        public int PostAbertoId { get; set; }
        public Usuario UserPost { get; set; }
        public Post PostOpen { get; set; }
        public Comentario ComentarioPost { get; set; }

    }

}
