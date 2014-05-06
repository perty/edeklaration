using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace Crisp.Tdd.EdeklarationTests.EndToEnd.Util
{
    public class SimpleOsCommandExecutor
    {
        private readonly string arguments;
        private readonly string fileName;

        private readonly IList<string> stderr = new List<string>();

        private readonly IList<string> stdout = new List<string>();

        public SimpleOsCommandExecutor(string fileName, string arguments)
        {
            this.fileName = fileName;
            this.arguments = arguments;
        }

        public IList<string> Stderr
        {
            get { return stderr; }
        }

        public IList<string> Stdout
        {
            get { return stdout; }
        }

        public void Run()
        {
            var startInfo = new ProcessStartInfo();
            startInfo.RedirectStandardError = true;
            startInfo.RedirectStandardOutput = true;
            startInfo.CreateNoWindow = false;
            startInfo.UseShellExecute = false;
            startInfo.FileName = fileName;
            startInfo.WindowStyle = ProcessWindowStyle.Hidden;
            if (!String.IsNullOrEmpty(arguments))
            {
                startInfo.Arguments = arguments;
            }

            using (Process process = Process.Start(startInfo))
            {
                process.ErrorDataReceived += StderrHandler;
                process.BeginErrorReadLine();

                process.OutputDataReceived += OutputHandler;
                process.BeginOutputReadLine();

                process.WaitForExit();
            }
        }

        private void OutputHandler(object sendingProcess, DataReceivedEventArgs outLine)
        {
            if (!String.IsNullOrEmpty(outLine.Data))
            {
                stdout.Add(outLine.Data);
            }
        }

        private void StderrHandler(object sendingProcess, DataReceivedEventArgs outLine)
        {
            if (!String.IsNullOrEmpty(outLine.Data))
            {
                stderr.Add(outLine.Data);
            }
        }
    }
}