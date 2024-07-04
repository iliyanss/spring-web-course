using InfluencerManagerApp.Core;
using InfluencerManagerApp.Core.Contracts;

namespace InfluencerManagerApp;

public class Program
{
    public static void Main(string[] args)
    {
        IEngine iEngine = new Engine();
        iEngine.Run();
    }
}
