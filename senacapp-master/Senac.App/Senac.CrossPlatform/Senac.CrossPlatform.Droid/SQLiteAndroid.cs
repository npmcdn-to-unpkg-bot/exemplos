using System;
using Xamarin.Forms;
using System.IO;
using Senac.CrossPlatform.Droid;

[assembly: Dependency(typeof(SQLiteAndroid))]
namespace Senac.CrossPlatform.Droid
{
    public class SQLiteAndroid : ISQLite
    {
        public SQLiteAndroid()
        {
        }

        public SQLite.SQLiteConnection GetConnection()
        {
            var sqliteFilename = "TokenSQLite2.db3";
            string documentsPath = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal); // Documents folder
            var path = Path.Combine(documentsPath, sqliteFilename);

            // This is where we copy in the prepopulated database
            Console.WriteLine(path);

            //if (!File.Exists(path))
            //{
            //    var s = Forms.Context.Resources.OpenRawResource(Resource.Raw.TokenSQLite);  // RESOURCE NAME ###

            //    // create a write stream
            //    FileStream writeStream = new FileStream(path, FileMode.OpenOrCreate, FileAccess.Write);
            //    // write to the stream
            //    ReadWriteStream(s, writeStream);
            //}

            var conn = new SQLite.SQLiteConnection(path);

            // Return the database connection 
            return conn;
        }
    }
}