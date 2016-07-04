using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Senac.CrossPlatform.Data
{
    public class SenacLite
    {
        static object locker = new object();

        SQLiteConnection database;

        /// <summary>
        /// Initializes a new instance of the <see cref="Tasky.DL.TaskDatabase"/> TaskDatabase. 
        /// if the database doesn't exist, it will create the database and all the tables.
        /// </summary>
        /// <param name='path'>
        /// Path.
        /// </param>
        public SenacLite()
        {
            database = DependencyService.Get<ISQLite>().GetConnection();
            // create the tables
            database.CreateTable<Token>();
        }

        public IEnumerable<Token> GetItems()
        {
            lock (locker)
            {
                return (from i in database.Table<Token>() select i).ToList();
            }
        }

        public Token GetItem(int id)
        {
            lock (locker)
            {
                return database.Table<Token>().FirstOrDefault(x => x.TokenID == id);
            }
        }

        public int SaveItem(Token item)
        {
            lock (locker)
            {
                if (item.TokenID != 0)
                {
                    database.Update(item);
                    return item.TokenID;
                }
                else {
                    return database.Insert(item);
                }
            }
        }

        public int DeleteItem(int id)
        {
            lock (locker)
            {
                return database.Delete<Token>(id);
            }
        }

        public int DeleteAll()
        {
            lock (locker)
            {
                return database.DeleteAll<Token>();
            }
        }
    }
}
