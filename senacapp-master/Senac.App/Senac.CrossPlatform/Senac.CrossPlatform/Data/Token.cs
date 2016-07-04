using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform.Data
{
    [Table("token")]
    public class Token
    {
        [PrimaryKey, AutoIncrement]
        public int TokenID { get; set; }
        public int UsuarioID { get; set; }
        public string TokenPrincipal { get; set; }
        public string RefreshToken{ get; set; }
    }
}
