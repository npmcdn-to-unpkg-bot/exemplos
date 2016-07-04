using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Model
{
    public class Comentario
    {
        public int ComentarioId { get; set; }
        public string Texto { get; set; }
        public Usuario Usuario { get; set; }
        public Post Post { get; set; }
    }
}