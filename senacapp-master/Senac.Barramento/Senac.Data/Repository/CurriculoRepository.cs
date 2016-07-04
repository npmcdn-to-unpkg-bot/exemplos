using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Repository
{
    public class CurriculoRepository
    {
        private SenacDB _db;
        private object curriculoEncontrado;
        private object curriculo;

        public CurriculoRepository()
        {
            _db = new SenacDB();
        }

        public bool InserirCurriculo(Curriculo curriculo)
        {
            _db.Curriculo.Add(curriculo);

            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }

        public bool AtualizarCurriculo()
        {
            throw new NotImplementedException();
        }

        public bool AtualizarCurriculo(Curriculo curriculo)
        {
            throw new NotImplementedException();
        }

        public bool ExcluirCurriculo()
        {
            throw new NotImplementedException();
        }

        public List<Curriculo> BuscarCurriculo()
        {
            throw new NotImplementedException();
        }

        public List<Curriculo> BuscarCurriculo(List<Curriculo> list)
        {
            return _db.Curriculo.ToList();
                ;
        }

        public bool EditarCurriculo(Curriculo curriculo)
        {
            var curriculoEncontrado = _db.Curriculo.Where(c => c.CurriculoID == curriculo.CurriculoID).FirstOrDefault();

            if (curriculoEncontrado != null)
            {
                curriculoEncontrado.Texto = curriculo.Texto;
            }
            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }

        public List<Curriculo> ListarCurriculo()
        {
            return _db.Curriculo.ToList();
        }

        public bool ExcluirCurriculo(int curriculoId)
        {
            var comentEncontrado = _db.Curriculo.Where(c => c.CurriculoID == curriculoId).FirstOrDefault();

            if (curriculoEncontrado != null)
            {
                _db.Curriculo.Remove(comentEncontrado);
            }

            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }



    }
}
