using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Model
{
    public class Curriculo
    {

        public int CurriculoID { get; set; }
        public string Nome { get; set; }
        public int Telefone { get; set; }
        public int Celular { get; set; }
        public string Empresa { get; set; }
        public string Funcao { get; set; }
        public string Curso { get; set; }
        public long NMatricula { get; set; }
        public string TerminoCurso { get; set; }
        public string DescricaoCurso { get; set; }
        public object Texto { get; internal set; }
    }
}
