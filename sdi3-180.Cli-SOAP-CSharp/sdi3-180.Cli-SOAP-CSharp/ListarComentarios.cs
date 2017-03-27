using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_180.Cli_SOAP_CSharp
{
    class ListarComentarios
    {
        private EjbAdminServiceService adminService = new EjbAdminServiceService();

        public void execute() {
		Console.WriteLine("ID - ViajeId - DestinoViaje - ComentadoPor - Sobre - Valoracion - Comentario");
		rating[] ratings = adminService.getComentarios();
        string format = "{0,-15} {1, -15} {2, -15} {3, -15} {4, -15} {5, -15} {6, -15}";
		//ordenar de lo mas reciente a menos.
		for (int i=ratings.Count()-1; i >= 0; i--) {
            string line = String.Format(format,
                    ratings.ElementAt(i).id, ratings.ElementAt(i).seatAboutTripId, adminService.getDestino(ratings.ElementAt(i).seatAboutTripId, true),
                    adminService.getUserLogin(ratings.ElementAt(i).seatFromUserId, true), adminService.getUserLogin(ratings.ElementAt(i).seatAboutUserId, true),
                    ratings.ElementAt(i).value, ratings.ElementAt(i).comment);
            Console.WriteLine(line);
		}
		
	}
    }
}
