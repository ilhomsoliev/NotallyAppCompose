package com.ilhomsoliev.noteapp.app

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.ilhomsoliev.noteapp.core.Constants
import com.ilhomsoliev.noteapp.core.Screens
import com.ilhomsoliev.noteapp.presentation.addNote.AddNoteScreen
import com.ilhomsoliev.noteapp.presentation.archive.ArchiveScreen
import com.ilhomsoliev.noteapp.presentation.home.HomeScreen
import com.ilhomsoliev.noteapp.presentation.label.LabelScreen
import com.ilhomsoliev.noteapp.presentation.settings.SettingsScreen
import com.ilhomsoliev.noteapp.presentation.trash.TrashScreen
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route?:""
    var isCreateLabelDialogActive by remember{ mutableStateOf(false) }
    // TODO
    /*BackHandler {
        if (scaffoldState.drawerState.isOpen) {
            coroutineScope.launch {
                scaffoldState.drawerState.close()
            }
        }else {

        }
    }*/
    Scaffold(scaffoldState = scaffoldState, topBar = {
        if(currentScreen == Screens.HomeScreen.route ||
            currentScreen == Screens.TrashScreen.route ||
            currentScreen == Screens.SettingsScreen.route ||
            currentScreen == Screens.LabelScreen.route ||
            currentScreen == Screens.ArchiveScreen.route ) {
            TopAppBar(elevation = 4.dp, title = {
                Text(
                    text = when (currentScreen) {
                        Screens.HomeScreen.route -> "Notes"
                        Screens.TrashScreen.route -> "Trash"
                        Screens.SettingsScreen.route -> "Settings"
                        Screens.LabelScreen.route -> "Label"
                        Screens.AddEditNoteScreen.route -> ""
                        Screens.ArchiveScreen.route -> "Archive"
                        else -> ""
                    }
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(Icons.Filled.Menu, null)
                }
            }, actions = {
                when (currentScreen) {
                    Screens.HomeScreen.route -> {
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.Search, null)
                        }
                    }
                    Screens.TrashScreen.route -> {
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.Search, null)
                        }
                    }
                    Screens.LabelScreen.route -> {
                        IconButton(onClick = {
                            isCreateLabelDialogActive = true
                        }) {
                            Icon(Icons.Filled.Add, null)
                        }
                    }
                    else -> {}
                }

            })
        }
    }, drawerGesturesEnabled = true, drawerContent = {
        DrawerContent(
            goToNotesScreen = {
                if (navController.currentDestination?.route != Screens.HomeScreen.route) {
                    navController.navigate(Screens.HomeScreen.route)
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            },
            goToLabelsScreen = {
                if (navController.currentDestination?.route != Screens.LabelScreen.route) {
                    navController.navigate(Screens.LabelScreen.route)
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }

            },
            goToTrashScreen = {
                if (navController.currentDestination?.route != Screens.TrashScreen.route) {
                    navController.navigate(Screens.TrashScreen.route)
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }

            },
            goToArchiveScreen = {
                if (navController.currentDestination?.route != Screens.ArchiveScreen.route) {
                    navController.navigate(Screens.ArchiveScreen.route)
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            },
            goToSettingsScreen = {
                if (navController.currentDestination?.route != Screens.SettingsScreen.route) {
                    navController.navigate(Screens.SettingsScreen.route)
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            },
            currentScreen = currentScreen,
        )
    }) {

        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = Screens.HomeScreen.route
        ) {
            composable(route = Screens.HomeScreen.route) {
                HomeScreen(goToAddScreen = { noteId ->
                    navController.navigate(
                        Screens.AddEditNoteScreen.route.replace(
                            "{${Constants.NOTE_ID_ARG}}",
                            "$noteId"
                        )
                    )
                })
            }
            composable(
                route = Screens.AddEditNoteScreen.route,
                arguments = listOf(navArgument(Constants.NOTE_ID_ARG) {
                    type = NavType.IntType
                })
            ) {
                AddNoteScreen(it.arguments?.getInt(Constants.NOTE_ID_ARG) ?: -1, {
                    navController.navigate(Screens.HomeScreen.route) {
                        popUpTo(Screens.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(route = Screens.LabelScreen.route) {
                LabelScreen(isCreateLabelDialogActive, onDismissRequest = {
                    isCreateLabelDialogActive = false
                })
            }
            composable(route = Screens.TrashScreen.route) {
                TrashScreen(goToAddScreen = { noteId ->
                    navController.navigate(
                        Screens.AddEditNoteScreen.route.replace(
                            "{${Constants.NOTE_ID_ARG}}",
                            "$noteId"
                        )
                    )
                })
            }
            composable(route = Screens.ArchiveScreen.route) {
                ArchiveScreen(goToAddScreen = { noteId ->
                    navController.navigate(
                        Screens.AddEditNoteScreen.route.replace(
                            "{${Constants.NOTE_ID_ARG}}",
                            "$noteId"
                        )
                    )
                })
            }
            composable(route = Screens.SettingsScreen.route) {
                SettingsScreen()
            }
        }
    }
}
