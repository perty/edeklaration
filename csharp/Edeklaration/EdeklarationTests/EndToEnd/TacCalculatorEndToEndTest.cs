using System.IO;
using Crisp.Tdd.EdeklarationTests.EndToEnd.Util;
using NUnit.Framework;

namespace Crisp.Tdd.EdeklarationTests.EndToEnd
{
    [TestFixture]
    public class TacCalculatorEndToEndTest
    {
        private string GetTestedExe()
        {
            return Path.Combine(Directory.GetParent(Directory.GetCurrentDirectory()).Parent.Parent.FullName,
                "EdeklarationConsole\\bin\\Debug\\EdeklarationConsole.exe");
        }

        [Test]
        public void IngenInkomstIngenSkatt()
        {
            var executor = new SimpleOsCommandExecutor(GetTestedExe(), "0 Stockholms");
            executor.Run();
            Assert.AreEqual("Slutlig skatt: 0,00 kr", executor.Stdout[0]);
        }

        [Test]
        public void IsTestedBinaryPresent()
        {
            Assert.IsTrue(File.Exists(GetTestedExe()));
        }
    }
}