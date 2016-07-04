using System;
using SQLite;
using System.IO;
using Senac.CrossPlatform.iOS;

[assembly: Xamarin.Forms.Dependency(typeof(SQLiteIOS))]
namespace Senac.CrossPlatform.iOS
{
    public class SQLiteIOS : ISQLite
    {
        public SQLiteConnection GetConnection()
        {
            var sqliteFilename = "TokenSQLite2.db3";
            string documentsPath = Environment.GetFolderPath(Environment.SpecialFolder.Personal); // Documents folder
            string libraryPath = Path.Combine(documentsPath, "..", "Library"); // Library folder
            var path = Path.Combine(libraryPath, sqliteFilename);
            // Create the connection
            var conn = new SQLite.SQLiteConnection(path);
            // Return the database connection
            return conn;
        }
    }
}
