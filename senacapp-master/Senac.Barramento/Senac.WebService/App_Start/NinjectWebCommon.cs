[assembly: WebActivatorEx.PreApplicationStartMethod(typeof(Senac.WebService.App_Start.NinjectWebCommon), "Start")]
[assembly: WebActivatorEx.ApplicationShutdownMethodAttribute(typeof(Senac.WebService.App_Start.NinjectWebCommon), "Stop")]

namespace Senac.WebService.App_Start
{
    using System;
    using System.Web;

    using Microsoft.Web.Infrastructure.DynamicModuleHelper;

    using Ninject;
    using Ninject.Web.Common;
    using Business.Interface;
    using Business.Business;
    using Data.Interface;
    using Data.Repository;
    using Data;
    public static class NinjectWebCommon 
    {
        private static readonly Bootstrapper bootstrapper = new Bootstrapper();

        /// <summary>
        /// Starts the application
        /// </summary>
        public static void Start() 
        {
            DynamicModuleUtility.RegisterModule(typeof(OnePerRequestHttpModule));
            DynamicModuleUtility.RegisterModule(typeof(NinjectHttpModule));
            bootstrapper.Initialize(CreateKernel);
        }
        
        /// <summary>
        /// Stops the application.
        /// </summary>
        public static void Stop()
        {
            bootstrapper.ShutDown();
        }
        
        /// <summary>
        /// Creates the kernel that will manage your application.
        /// </summary>
        /// <returns>The created kernel.</returns>
        private static IKernel CreateKernel()
        {
            var kernel = new StandardKernel();
            try
            {
                kernel.Bind<Func<IKernel>>().ToMethod(ctx => () => new Bootstrapper().Kernel);
                kernel.Bind<IHttpModule>().To<HttpApplicationInitializationHttpModule>();

                RegisterServices(kernel);
                return kernel;
            }
            catch
            {
                kernel.Dispose();
                throw;
            }
        }

        /// <summary>
        /// Load your modules or register your services here!
        /// </summary>
        /// <param name="kernel">The kernel.</param>
        private static void RegisterServices(IKernel kernel)
        {
            //Uow
            kernel.Bind<ISenacDB>().To<SenacDB>();

            kernel.Bind<IUnitOfWork>().To<UnitOfWork>();

            //Inje��o da camada de repository
            kernel.Bind<IAuthRepository>().To<AuthRepository>();

            kernel.Bind<IComentarioRepository>().To<ComentarioRepository>();

            kernel.Bind<IPostRepository>().To<PostRepository>();

            kernel.Bind<IUsuarioRepository>().To<UsuarioRepository>();

            //Inje��o da camada de neg�cios
            kernel.Bind<IAuthBusiness>().To<AuthBusiness>();

            kernel.Bind<IComentarioBusiness>().To<ComentarioBusiness>();

            kernel.Bind<IPostBusiness>().To<PostBusiness>();

            kernel.Bind<IUsuarioBusiness>().To<UsuarioBusiness>();
        }        
    }
}
