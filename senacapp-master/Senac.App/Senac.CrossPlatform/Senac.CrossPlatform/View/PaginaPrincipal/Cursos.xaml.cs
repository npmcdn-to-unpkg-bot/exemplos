using Senac.CrossPlatform.Service;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

namespace Senac.CrossPlatform.View.PaginaPrincipal
{
    public partial class Cursos : ContentPage
    {
        private List<Curso> _cursos;
        private CursoService _service;
        public Cursos()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
            _service = new CursoService();
            _cursos = _service.ListarPosts();
            listaCursos.ItemsSource = _cursos;
        }

        public void AtualizarCursos(Object sender, EventArgs e)
        {
            _cursos = _service.ListarPosts();
            listaCursos.ItemsSource = _cursos;
            listaCursos.EndRefresh();

        }

        public void IrCursoAberto(Object sender, EventArgs e)
        {
            var itemId = ((Curso)listaCursos.SelectedItem).CursoID;
            Navigation.PushAsync(new View.PaginaPrincipal.CursoDados(itemId));
        }
    }
}
