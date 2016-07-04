using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform.Service
{
    public class CursoService
    {
        public List<Curso> ListarPosts()
        {
            Curso c1 = new Curso();
            Curso c2 = new Curso();
            Curso c3 = new Curso();

            c1.Nome = "Técnico em Computação Gráfica";
            c1.Duracao = "Duração: 1000 horas";
            c1.TipoCurso = "Informática";

            c2.Nome = "Técnico em Transações Imobiliárias";
            c2.Duracao = "Duração: 960 horas";
            c2.TipoCurso = "Comércio";

            c3.Nome = "Técnico em Análises Clínicas";
            c3.Duracao = "Duração: 1200 horas";
            c3.TipoCurso = "Saúde";

            List<Curso> cursos = new List<Curso>();
            cursos.Add(c1);
            cursos.Add(c2);
            cursos.Add(c3);
            return cursos;
        }
    }
}
