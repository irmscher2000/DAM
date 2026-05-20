; Eugen Moga

[Setup]
AppName=FormularioEmpleados
AppVersion=1.0
DefaultDirName={autopf}\FormularioEmpleados
Compression=lzma
SolidCompression=yes
SetupIconFile=icono.ico
WizardImageFile=fondo.bmp
LicenseFile=licencia.txt
OutputBaseFilename=Setup
SignTool=miCert
SignedUninstaller=yes

[Files]
Source: "bin\Release\*.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "bin\Release\*.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "bin\Release\*.rdlc"; DestDir: "{app}"; Flags: ignoreversion skipifsourcedoesntexist

[Tasks]
Name: "desktopicon"; Description: "¿Deseas crear un acceso directo en el escritorio?"; GroupDescription: "Opciones adicionales"; Flags: unchecked
Name: "startmenuicon"; Description: "¿Desea crear un acceso directo en el menú Inicio?"; GroupDescription: "Opciones adicionales"; Flags: checkedonce

[Icons]
Name: "{group}\FormularioEmpleados"; Filename: "{app}\Formulario_WPF.exe"
Name: "{userdesktop}\FormularioEmpleados"; Filename: "{app}\Formulario_WPF.exe"; Tasks: desktopicon

[CustomMessages]
miCert="C:\Program Files (x86)\Windows Kits\10\bin\10.0.28000.0\x64\signtool.exe" sign /f "C:\Users\Moga\certificado.pfx" /p 123456 /td sha256 /fd sha256 $f