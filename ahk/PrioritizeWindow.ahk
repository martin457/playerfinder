Loop, {
    sleep 1000
    if WinActive("GTA:SA:MP") {
		if WinExist("Playerfinder") {
			WinSet, AlwaysOnTop, On, Playerfinder
		}
	}
}