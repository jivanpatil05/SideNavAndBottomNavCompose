package com.example.sidenavigaionbottomnavigationcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home
        ),
        NavigationItem(
            title = "Email",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Filled.Email,
        ),
        NavigationItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountBox,
            unselectedIcon = Icons.Filled.AccountBox
        ),
        NavigationItem(
            title = "Exit",
            selectedIcon = Icons.Filled.ExitToApp,
            unselectedIcon = Icons.Filled.ExitToApp
        )
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(320.dp),
                drawerContainerColor = Color.DarkGray,
            ) {
                Row(
                    modifier = Modifier
                        .width(380.dp)
                        .height(157.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background11),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.3f))
                                .padding(16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.profile),
                                    contentDescription = "avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Column(
                                    modifier = Modifier.padding(bottom = 6.dp)
                                ) {
                                    Text(
                                        text = "Jivan Patil",
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = "muscles",
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                }

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                items.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex
                    val backgroundColor = if (isSelected) Brush.linearGradient(
                        colors = listOf(Color.Cyan, Color.Transparent),
                        start = Offset(0f, 0f),
                        end = Offset(700f, 200f)
                    ) else Brush.linearGradient(colors = listOf(Color.DarkGray, DarkGray))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 3.dp, bottom = 3.dp, end = 10.dp)
                            .background(backgroundColor)
                            .clickable(onClick = {
                                selectedIndex = index
                                scope.launch { drawerState.close() }
                            }),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            tint = if(isSelected){
                                Color.Black}else{
                                Color.White},
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = item.title,
                            color =if(isSelected){
                                Color.Black}else{
                                Color.White},
                            modifier = Modifier.weight(1f)
                        )
                        if (item.notificationCount != null) {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(24.dp)
                                    .background(
                                        if (isSelected) {
                                            Color.Black
                                        } else {
                                            Color.White
                                        }, shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.notificationCount.toString(),
                                    color = if(isSelected){
                                        Color.Black}else{
                                        Color.White},
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Row(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            scope.launch {
                                drawerState.open()
                            }
                        })
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.leftlogo),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            "TODAY",
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.rightlogo1),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(15.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.targetlogo),
                        contentDescription = ""
                    )
                }


            },
            bottomBar = {
                val bottomNavMenu = listOf(
                    BottomNavItems.ShopScreen,
                    BottomNavItems.NutritionScreen,
                    BottomNavItems.DashboardScreen,
                    BottomNavItems.FriendsConnectScreen,
                    BottomNavItems.ProfileScreen
                )
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    containerColor = Color.Gray
                ) {
                    bottomNavMenu.forEach { screen ->
                        NavigationBarItem(selected = false ,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(
                                    painterResource(id = screen.icon),
                                    modifier = Modifier.height(34.dp),
                                    contentDescription = "BottomNavigation Icon",
                                    tint = Color.Unspecified
                                )
                            },
                            alwaysShowLabel = true,
                            colors = NavigationBarItemDefaults.colors(
                                unselectedIconColor = Color.Gray,
                                selectedIconColor = Color.Cyan
                            )
                        )
                    }
                }
            },
            content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    NavHostContainer(navController = navController, padding = it)
                }
            }
        )
    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController, padding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = BottomNavItems.DashboardScreen.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(BottomNavItems.DashboardScreen.route) {
            Dashboard()
        }
        composable(BottomNavItems.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(BottomNavItems.NutritionScreen.route) {
            NutritionScreen()
        }
        composable(BottomNavItems.FriendsConnectScreen.route) {
            FriendsConnectScreen()
        }
        composable(BottomNavItems.ShopScreen.route) {
            ShopScreen()
        }
    }
}

sealed class BottomNavItems(val route: String, val title: String, val icon: Int) {
    object ShopScreen : BottomNavItems("shop", "Shop", R.drawable.ic_launcher_background)
    object NutritionScreen : BottomNavItems("nutrition", "Nutrition", R.drawable.ic_launcher_background)
    object DashboardScreen : BottomNavItems("dashboard", "dashboard", R.drawable.ic_launcher_background)
    object FriendsConnectScreen : BottomNavItems("friendsConnect", "FriendsConnect", R.drawable.ic_launcher_background)
    object ProfileScreen : BottomNavItems("profile", "Profile", R.drawable.profile)
}


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val notificationCount: Int? = null
)


@Composable
fun Dashboard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow)
    ) {

    }
}
@Composable
fun ShopScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Cyan)
    ) {

    }
}
@Composable
fun NutritionScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Red)
    ) {

    }
}
@Composable
fun FriendsConnectScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Magenta)
    ) {

    }
}
@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Blue)
    ) {

    }
}