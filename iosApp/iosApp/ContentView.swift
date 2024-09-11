import SwiftUI
import shared

struct ContentView: View {
    
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false


	var body: some View {
        let articleScreen =  ArticlesScreen(viewModel: .init())
        
        NavigationStack {
            articleScreen
                .toolbar {
                    ToolbarItem {
                        Button {
                         shouldOpenSources = true
                        } label: {
                            Label("Sources", systemImage: "list.bullet.rectangle").labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenSources) {
                            SourcesScreen(viewModelWrapper: .init())
                         }
                        }
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
                            AboutScreen()
                        }
                    }
                }
        }.refreshable {
            articleScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
        }
        
	}
}


