package main.java.chattingSystem.App;

import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomViewManager;
import main.java.chattingSystem.interface_adapter.controllers.LogOutController;
import main.java.chattingSystem.interface_adapter.presenter.ChatRoomPresenter;
import main.java.chattingSystem.interface_adapter.view_models.*;
import main.java.chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import main.java.chattingSystem.use_cases.log_out.LogOutInputBoundary;
import main.java.chattingSystem.use_cases.log_out.LogOutInteractor;
import main.java.chattingSystem.use_cases.log_out.LogOutOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ChatRoomFrameFactory {
    private static JFrame currentChatRoomFrame;
    public static void createChatRoomFrame(ChatRoomViewModel chatRoomViewModel, LogOutDataAccessBoundary logOutDataAccessBoundary){
        JFrame chatRoomFrame = new JFrame("Chat Room");
        chatRoomFrame.setVisible(false);
        chatRoomFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout chatRoomCardLayout = new CardLayout();
        JPanel chatRoomViews = new JPanel(chatRoomCardLayout);
        chatRoomFrame.add(chatRoomViews);
        // This keeps track of and manages which view is currently showing.
        ChatRoomViewManagerModel chatRoomViewManagerModel = new ChatRoomViewManagerModel();
        ChatRoomViewManager chatRoomViewManager = new ChatRoomViewManager(chatRoomViews, chatRoomCardLayout, chatRoomViewManagerModel);
        LogOutController logOutController = createLogOutUseCase(logOutDataAccessBoundary);
        ChatRoomView chatRoomView = new ChatRoomView(chatRoomViewModel, chatRoomViewManagerModel, chatRoomViewManager, logOutController);
        chatRoomViews.add(chatRoomView, chatRoomView.viewName);
        chatRoomFrame.pack();
        chatRoomFrame.setVisible(true);
        chatRoomViewModel.setCurrentframe(chatRoomFrame);
        currentChatRoomFrame = chatRoomFrame;
    }

    public static LogOutController createLogOutUseCase(
            LogOutDataAccessBoundary logOutDataAccessBoundary
            ) {

        // Notice how we pass this method's parameters to the Presenter.
        LogOutOutputBoundary logOutOutputBoundary = new ChatRoomPresenter();

        LogOutInputBoundary logOutInteractor = new LogOutInteractor(logOutOutputBoundary, logOutDataAccessBoundary);

        return new LogOutController(logOutInteractor);
    }

}
