using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform.ViewModel
{
    public class Comentario
    {
        public int ComentarioID { get; set; }
        public string Texto { get; set; }
        public Usuario Usuario { get; set; }
        public Post Post { get; set; }
    }
}
