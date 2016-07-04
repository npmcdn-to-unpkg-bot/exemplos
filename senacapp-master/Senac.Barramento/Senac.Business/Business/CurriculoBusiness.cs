using Senac.Data.Model;
using Senac.Data.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Business
{
    public class CurriculoBusiness
    {

        private CurriculoRepository _curriculoRepository;

        public CurriculoBusiness()
        {
            _curriculoRepository = new CurriculoRepository();
        }

        public bool InserirCurriculo(Curriculo curriculo)
        {
            return _curriculoRepository.InserirCurriculo(curriculo);
        }

        public bool AtualizarCurriculo(CurriculoBusiness curriculo)
        {
            //fazer
            return _curriculoRepository.AtualizarCurriculo();
        }


        public List<Curriculo> BuscarCurriculos()
        {
            //fazer
            return _curriculoRepository.BuscarCurriculo();
        }

        public bool ExcluirCurriculo(int curriculoId)
        {
            return _curriculoRepository.ExcluirCurriculo();
        }
    }
}
