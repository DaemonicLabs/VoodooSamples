# Voodoo Sample

This is a temporary sample using gradle and a conventional IDE setup
while kscript is not quite ready
https://github.com/holgerbrandl/kscript/issues/166

run the test pack with
```bash
./gradew run --args "quickbuild -- pack sk"
```

or 

```bash
kscript src/TestPack.kt quickbuild
```
using `--` to separate jobs will nt work there.. forgot shells do this too
will need to replace that with a different separator

get kscript here: https://github.com/holgerbrandl/kscript  
or here: https://aur.archlinux.org/packages/kscript/